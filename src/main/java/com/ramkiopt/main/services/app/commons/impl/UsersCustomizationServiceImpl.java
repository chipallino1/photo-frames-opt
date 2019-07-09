package com.ramkiopt.main.services.app.commons.impl;

import com.ramkiopt.main.dto.UsersDto;
import com.ramkiopt.main.services.app.commons.UsersCustomizationService;
import com.ramkiopt.main.services.app.users.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersCustomizationServiceImpl implements UsersCustomizationService {

    private final UsersService<UsersDto> usersService;

    @Autowired
    public UsersCustomizationServiceImpl(UsersService<UsersDto> usersService) {
        this.usersService = usersService;
    }

    @Override
    public UsersDto createUser(UsersDto dto) {
        return usersService.create(dto);
    }
}
