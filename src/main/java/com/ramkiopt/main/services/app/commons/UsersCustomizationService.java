package com.ramkiopt.main.services.app.commons;

import com.ramkiopt.main.dto.UsersDto;

public interface UsersCustomizationService {
    UsersDto createUser(UsersDto dto);

    UsersDto readUser(Long id);

    UsersDto readUserByEmail(String email);

    UsersDto updateUser(Long id, UsersDto usersDto);

    Boolean deleteUser(Long id);
}
