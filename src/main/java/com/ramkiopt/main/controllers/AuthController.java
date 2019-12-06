package com.ramkiopt.main.controllers;

import com.ramkiopt.main.configuration.JwtProvider;
import com.ramkiopt.main.dto.AuthBodyDto;
import com.ramkiopt.main.entities.Users;
import com.ramkiopt.main.repositories.UsersRepository;
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

import javax.persistence.EntityNotFoundException;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;

    private final JwtProvider jwtProvider;

    private final UsersRepository usersRepository;

    private final BaseResponseService responseService;

    public AuthController(AuthenticationManager authenticationManager, JwtProvider jwtProvider,
                          UsersRepository usersRepository, BaseResponseService responseService) {
        this.authenticationManager = authenticationManager;
        this.jwtProvider = jwtProvider;
        this.usersRepository = usersRepository;
        this.responseService = responseService;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AuthBodyDto bodyDto) {

        String username = bodyDto.getEmail();
        try {
            authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(username, bodyDto.getPassword()));
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid email/password supplied");
        }
        Users users = usersRepository.findByEmail(username);
        if (users == null) {
            throw new EntityNotFoundException();
        }
        String token = jwtProvider.createToken(username, usersRepository.findByEmail(username).getRole());
        Map<Object, Object> model = new HashMap<>();
        model.put("username", username);
        model.put("token", token);
        return responseService.createResponseEntity(model, HttpStatus.OK);
    }
}
