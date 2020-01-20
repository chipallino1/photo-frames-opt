package com.ramkiopt.main.services.app.users;

import java.util.List;

public interface UsersService<T> extends UsersCrudService<T>, UsersGetterService<T> {
    List<String> getRoles(String username);
}
