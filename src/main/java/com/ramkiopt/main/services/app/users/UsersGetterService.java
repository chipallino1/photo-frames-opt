package com.ramkiopt.main.services.app.users;

public interface UsersGetterService<T> {
    T getByEmail(String email);
}
