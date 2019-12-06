package com.ramkiopt.main.controllers;

import com.ramkiopt.main.dto.UsersDto;
import com.ramkiopt.main.services.app.commons.UsersCustomizationService;
import com.ramkiopt.main.services.utils.response.BaseResponseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/users")
public class UsersController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UsersController.class);
    private final UsersCustomizationService usersCustomizationService;
    private final BaseResponseService responseService;

    @Autowired
    public UsersController(UsersCustomizationService usersCustomizationService, BaseResponseService responseService) {
        this.usersCustomizationService = usersCustomizationService;
        this.responseService = responseService;
    }

    @GetMapping("/me")
    public ResponseEntity getCurrentUser(Principal principal) {
        return responseService.createResponseEntity(usersCustomizationService.readUserByEmail(principal.getName()),
                HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity createUser(@RequestBody UsersDto usersDto) {
        usersDto = usersCustomizationService.createUser(usersDto);
        return responseService.createResponseEntity(usersDto, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity readUser(@PathVariable("id") Long id) {
        return responseService.createResponseEntity(usersCustomizationService.readUser(id), HttpStatus.OK);
    }

    @PutMapping("/{id}/update")
    public ResponseEntity updateUser(@PathVariable("id") Long id, @RequestBody UsersDto usersDto) {
        return responseService.createResponseEntity(usersCustomizationService.updateUser(id, usersDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable("id") Long id) {
        return responseService.createResponseEntity(usersCustomizationService.deleteUser(id), HttpStatus.OK);
    }
}
