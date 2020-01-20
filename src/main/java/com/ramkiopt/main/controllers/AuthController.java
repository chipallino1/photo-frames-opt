package com.ramkiopt.main.controllers;

import com.ramkiopt.main.configuration.JwtProvider;
import com.ramkiopt.main.dto.AuthBodyDto;
import com.ramkiopt.main.dto.UsersDto;
import com.ramkiopt.main.services.app.users.UsersService;
import com.ramkiopt.main.services.security.UserRole;
import com.ramkiopt.main.services.utils.response.BaseResponseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;
    private final BaseResponseService responseService;
    private final UsersService<UsersDto> usersService;

    public AuthController(AuthenticationManager authenticationManager, JwtProvider jwtProvider,
                          BaseResponseService responseService, UsersService<UsersDto> usersService) {
        this.authenticationManager = authenticationManager;
        this.jwtProvider = jwtProvider;
        this.responseService = responseService;
        this.usersService = usersService;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AuthBodyDto bodyDto) {
        String username = bodyDto.getEmail();
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, bodyDto.getPassword()));
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid email/password supplied");
        }
        String token = jwtProvider.createToken(username, UserRole.valueOf(usersService.getRoles(username).get(0)));
        return responseService.createResponseEntity(createResponseTokenDto(username, token), HttpStatus.OK);
    }

    private Object createResponseTokenDto(String username, String token) {
        Map<Object, Object> model = new HashMap<>();
        model.put("username", username);
        model.put("token", token);
        return model;
    }
}
