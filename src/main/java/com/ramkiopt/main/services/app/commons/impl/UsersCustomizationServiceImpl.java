package com.ramkiopt.main.services.app.commons.impl;

import com.ramkiopt.main.dto.UsersDto;
import com.ramkiopt.main.services.app.commons.UsersCustomizationService;
import com.ramkiopt.main.services.app.users.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;

@Service
public class UsersCustomizationServiceImpl implements UsersCustomizationService {

    @Autowired
    private UsersService usersService;

    @Override
    public Boolean createUser(UsersDto dto) {
        Boolean isCreated = null;
        try {
            isCreated = usersService.create(dto);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return isCreated;
    }
}
