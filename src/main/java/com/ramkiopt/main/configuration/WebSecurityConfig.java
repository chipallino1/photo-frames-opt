package com.ramkiopt.main.configuration;

import com.ramkiopt.main.repositories.HttpCookieOAuth2AuthorizationRequestRepository;
import com.ramkiopt.main.services.security.CustomOAuth2UserService;
import com.ramkiopt.main.services.security.CustomUserDetailsService;
import com.ramkiopt.main.services.security.OAuth2AuthenticationFailureHandler;
import com.ramkiopt.main.services.security.OAuth2AuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = true,
        prePostEnabled = true
)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtProvider jwtProvider;

    @Autowired
    private CustomOAuth2UserService customOAuth2UserService;
    @Autowired
    private OAuth2AuthenticationFailureHandler failureHandler;
    @Autowired
    private OAuth2AuthenticationSuccessHandler successHandler;

    public WebSecurityConfig(JwtProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        UserDetailsService userDetailsService = mysqlUserDetails();
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .cors()
                .and()
                .httpBasic()
                .disable()
                .csrf()
                .disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/auth/login").permitAll()
                .antMatchers("/users/create").permitAll()
                .antMatchers("/photo-frames/**").permitAll()
                .antMatchers("/sizes/**").permitAll()
                .antMatchers("/colors/**").permitAll()
                .antMatchers("/auth/**", "/oauth2/**")
                .permitAll()
                .antMatchers("/photo-frames/create").hasAuthority("MAIN").anyRequest().authenticated()
                //.and()
                //.apply(new JwtConfigurer(jwtProvider))
                .and()
                .csrf().disable()
                .exceptionHandling().authenticationEntryPoint(unauthorizedEntryPoint())
                .and()
                .oauth2Login()
                .authorizationEndpoint()
                .baseUri("/oauth2/authorize")
                .authorizationRequestRepository(cookieAuthorizationRequestRepository())
                .and()
                .redirectionEndpoint()
                .baseUri("/login/oauth2/code/google")
                .and()
                .userInfoEndpoint()
                .userService(customOAuth2UserService)
                .and()
                .failureHandler(failureHandler)
                .successHandler(successHandler);

        http.addFilterBefore(tokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
    }

    @Bean
    public ClientRegistrationRepository clientRegistrationRepository() {
        return new InMemoryClientRegistrationRepository(Arrays.asList(CommonOAuth2Provider.GOOGLE.getBuilder("google")
                .clientId("439082142714-hstf4p7s9msp50hstf7ngr6ol636sssc.apps.googleusercontent.com")
                .clientSecret("4bIhg9IXKpnoAeY_-BiP4X0B")
                .scope("email", "profile")
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .tokenUri("https://accounts.google.com/o/oauth2/token")
                .authorizationUri("https://accounts.google.com/o/oauth2/v2/auth")
                .userInfoUri("https://www.googleapis.com/oauth2/v3/userinfo")
                .redirectUriTemplate("http://localhost:8080/login/oauth2/code/google")
                .build()));
    }

    /*
      By default, Spring OAuth2 uses HttpSessionOAuth2AuthorizationRequestRepository to save
      the authorization request. But, since our service is stateless, we can't save it in
      the session. We'll save the request in a Base64 encoded cookie instead.
    */
    @Bean
    public HttpCookieOAuth2AuthorizationRequestRepository cookieAuthorizationRequestRepository() {
        return new HttpCookieOAuth2AuthorizationRequestRepository();
    }

    @Bean
    public TokenAuthenticationFilter tokenAuthenticationFilter() {
        return new TokenAuthenticationFilter();
    }

    @Bean
    public PasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public AuthenticationEntryPoint unauthorizedEntryPoint() {
        return (request, response, authException) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED,
                "Unauthorized");
    }

    @Bean
    public UserDetailsService mysqlUserDetails() {
        return new CustomUserDetailsService();
    }
}