package com.ramkiopt.main.services.app.commons.impl;

import com.ramkiopt.main.dto.UsersDto;
import com.ramkiopt.main.services.app.base.RowStatus;
import com.ramkiopt.main.services.app.commons.UsersCustomizationService;
import com.ramkiopt.main.services.app.users.UsersService;
import com.ramkiopt.main.services.security.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Collections;

@Service
public class UsersCustomizationServiceImpl implements UsersCustomizationService {

    private final UsersService<UsersDto> usersService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UsersCustomizationServiceImpl(UsersService<UsersDto> usersService, PasswordEncoder passwordEncoder) {
        this.usersService = usersService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UsersDto createUser(UsersDto dto) {
        dto.setRoles(dto.getRoles() == null ? Collections.singletonList(UserRole.USER) : dto.getRoles());
        dto.setPasswordEncrypted(dto.getPassword() == null ? null : passwordEncoder.encode(dto.getPassword()));
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
