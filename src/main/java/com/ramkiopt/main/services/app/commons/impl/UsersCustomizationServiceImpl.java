package com.ramkiopt.main.services.app.commons.impl;

import com.ramkiopt.main.dto.UsersDto;
import com.ramkiopt.main.services.app.commons.UsersCustomizationService;
import com.ramkiopt.main.services.app.users.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersCustomizationServiceImpl implements UsersCustomizationService {

    @Autowired
    private UsersService usersService;

    @Override
    public Boolean createUser(UsersDto dto) {
        Boolean isCreated = usersService.createUser(dto);
        return isCreated;
    }
}
