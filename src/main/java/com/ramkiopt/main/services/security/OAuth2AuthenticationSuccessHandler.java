package com.ramkiopt.main.services.security;

import com.ramkiopt.main.configuration.JwtProvider;
import com.ramkiopt.main.dto.UsersDto;
import com.ramkiopt.main.repositories.HttpCookieOAuth2AuthorizationRequestRepository;
import com.ramkiopt.main.services.app.commons.UsersCustomizationService;
import com.ramkiopt.main.services.utils.CookieUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.Optional;

import static com.ramkiopt.main.repositories.HttpCookieOAuth2AuthorizationRequestRepository.REDIRECT_URI_PARAM_COOKIE_NAME;

@Component
public class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private JwtProvider tokenProvider;
    private UsersCustomizationService usersCustomizationService;
    private HttpCookieOAuth2AuthorizationRequestRepository httpCookieOAuth2AuthorizationRequestRepository;


    @Autowired
    public OAuth2AuthenticationSuccessHandler(JwtProvider tokenProvider,
                                              UsersCustomizationService usersCustomizationService, HttpCookieOAuth2AuthorizationRequestRepository httpCookieOAuth2AuthorizationRequestRepository) {
        this.tokenProvider = tokenProvider;
        this.usersCustomizationService = usersCustomizationService;
        this.httpCookieOAuth2AuthorizationRequestRepository = httpCookieOAuth2AuthorizationRequestRepository;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        String targetUrl = determineTargetUrl(request, response, authentication);

        if (response.isCommitted()) {
            logger.debug("Response has already been committed. Unable to redirect to " + targetUrl);
            return;
        }

        clearAuthenticationAttributes(request, response);
        getRedirectStrategy().sendRedirect(request, response, targetUrl);
    }

    private void createOrUpdateUser(Authentication authentication) {
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        UsersDto usersDto = usersCustomizationService.readUserByEmail(oAuth2User.getAttribute("email"));
        if (usersDto == null) {
            usersDto = new UsersDto();
            usersDto.setEmail(oAuth2User.getAttribute("email"));
            usersDto.setFirstName(oAuth2User.getAttribute("given_name"));
            usersDto.setLastName(oAuth2User.getAttribute("last_name"));
            usersCustomizationService.createUser(usersDto);
        } else {
            usersDto.setEmail(oAuth2User.getAttribute("email"));
            usersDto.setFirstName(oAuth2User.getAttribute("given_name"));
            usersDto.setLastName(oAuth2User.getAttribute("last_name"));
            usersCustomizationService.updateUser(usersDto.getId(), usersDto);
        }
    }


    protected String determineTargetUrl(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        Optional<String> redirectUri = CookieUtils.getCookie(request, REDIRECT_URI_PARAM_COOKIE_NAME)
                .map(Cookie::getValue);

        if (redirectUri.isPresent() && !isAuthorizedRedirectUri(redirectUri.get())) {
            throw new BadCredentialsException("Sorry! We've got an Unauthorized Redirect URI and can't proceed with the authentication");
        }

        String targetUrl = redirectUri.orElse(getDefaultTargetUrl());
        createOrUpdateUser(authentication);
        OAuth2User auth2User = (OAuth2User) authentication.getPrincipal();
        String token = tokenProvider.createToken(auth2User.getAttribute("email"), UserRole.USER);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return UriComponentsBuilder.fromUriString(targetUrl)
                .queryParam("token", token)
                .build().toUriString();
    }

    protected void clearAuthenticationAttributes(HttpServletRequest request, HttpServletResponse response) {
        super.clearAuthenticationAttributes(request);
        httpCookieOAuth2AuthorizationRequestRepository.removeAuthorizationRequestCookies(request, response);
    }

    private boolean isAuthorizedRedirectUri(String uri) {
        URI clientRedirectUri = URI.create(uri);

        return true;
    }
}