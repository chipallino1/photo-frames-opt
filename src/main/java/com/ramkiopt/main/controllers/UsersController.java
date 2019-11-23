package com.ramkiopt.main.controllers;

import com.ramkiopt.main.dto.UsersDto;
import com.ramkiopt.main.services.app.commons.UsersCustomizationService;
import com.ramkiopt.main.services.utils.response.BaseResponseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
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

    @PostMapping("/create")
    public ResponseEntity createUser(@RequestBody UsersDto usersDto) {
        usersDto = usersCustomizationService.createUser(usersDto);
        return responseService.createResponseEntity(usersDto, HttpStatus.OK);
    }
}
