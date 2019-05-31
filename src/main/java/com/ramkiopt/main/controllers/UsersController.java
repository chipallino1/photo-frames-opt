package com.ramkiopt.main.controllers;

import com.ramkiopt.main.dto.UsersDto;
import com.ramkiopt.main.services.app.commons.UsersCustomizationService;
import com.ramkiopt.main.services.utils.ResponseCustomizationService;
import com.ramkiopt.main.services.utils.StatusMessages;
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
public class UsersController implements ResponseCustomizationService {

    @Autowired
    private UsersCustomizationService usersCustomizationService;
    private static final Logger LOGGER = LoggerFactory.getLogger(UsersController.class);

    @PostMapping("/")
    public ResponseEntity createUser(@RequestBody UsersDto usersDto) {
        Boolean isCreated = usersCustomizationService.createUser(usersDto);
        if (!isCreated) {
            return info(StatusMessages.SERVER_ENTITY_CREATION_ERROR, 500);
        }
        return getResponseEntity(isCreated, null, HttpStatus.OK);
    }
}
