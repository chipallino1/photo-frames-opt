package com.ramkiopt.main.services.app.commons.impl;

import com.ramkiopt.main.dto.UsersDto;
import com.ramkiopt.main.services.app.base.RowStatus;
import com.ramkiopt.main.services.app.commons.UsersCustomizationService;
import com.ramkiopt.main.services.app.users.UsersService;
import com.ramkiopt.main.services.security.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
        dto.setRole(dto.getRole() == null ? UserRole.USER : dto.getRole());
        dto.setPasswordEncrypted(dto.getPassword() == null ? null :
                new BCryptPasswordEncoder().encode(dto.getPassword()));
        return usersService.create(dto);
    }

    @Override
    public UsersDto readUser(Long id) {
        if (usersService.get(id) == null || usersService.get(id).getStatus().equals(RowStatus.DELETED)) {
            throw new EntityNotFoundException();
        }
        return usersService.get(id);
    }

    @Override
    public UsersDto readUserByEmail(String email) {
        return usersService.getByEmail(email);
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
