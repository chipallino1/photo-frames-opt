package com.ramkiopt.main.services.app.commons.impl;

import com.ramkiopt.main.dto.UsersDto;
import com.ramkiopt.main.services.app.base.RowStatus;
import com.ramkiopt.main.services.app.commons.UsersCustomizationService;
import com.ramkiopt.main.services.app.users.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

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

    @Override
    public UsersDto readUser(Long id) {
        if (usersService.get(id).getRowStatus().equals(RowStatus.DELETED)) {
            throw new EntityNotFoundException();
        }
        return usersService.get(id);
    }

    @Override
    public UsersDto updateUser(Long id, UsersDto usersDto) {
        return usersService.update(id, usersDto);
    }

    @Override
    public Boolean deleteUser(Long id) {
        return usersService.delete(id);
    }


}
