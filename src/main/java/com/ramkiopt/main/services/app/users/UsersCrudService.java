package com.ramkiopt.main.services.app.users;

import com.ramkiopt.main.dto.UsersDto;

public interface UsersCrudService {
    Boolean createUser(UsersDto dto);

    UsersDto getUsersDto(Long id);

    Boolean updateUser(Long id, UsersDto dto);

    Boolean deleteUser(Long id);
}
