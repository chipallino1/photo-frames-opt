package com.ramkiopt.main.services.app.users.impl;

import com.ramkiopt.main.dto.UsersDto;
import com.ramkiopt.main.entities.Users;
import com.ramkiopt.main.repositories.UsersRepository;
import com.ramkiopt.main.services.app.users.UsersService;
import com.ramkiopt.main.services.utils.BaseServiceAbstract;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.lang.reflect.InvocationTargetException;

@Service
public class UsersServiceImpl extends BaseServiceAbstract<Users, Long>
        implements UsersService {

    private final Logger LOGGER = LogManager.getLogger(UsersServiceImpl.class);
    @Autowired
    private UsersRepository usersRepository;

    @PostConstruct
    public void init() {
        setJpaRepository(usersRepository);
        setClass(Users.class);
    }

    @Override
    protected void setJpaRepository(JpaRepository<Users, Long> jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    protected void setClass(Class<Users> usersClass) {
        this.tClass = usersClass;
    }

    @Override
    public Boolean createUser(UsersDto dto) {
        Boolean isCreated = false;
        try {
            isCreated = tryCreate(dto);
        } catch (InvocationTargetException | NoSuchMethodException | InstantiationException
                | IllegalAccessException e) {
            LOGGER.error("Internal exception was generated.");
            return isCreated;
        }
        return isCreated;

    }

    @Override
    public UsersDto getUsersDto(Long id) {
        return null;
    }

    @Override
    public Boolean updateUser(Long id, UsersDto dto) {
        return null;
    }

    @Override
    public Boolean deleteUser(Long id) {
        return null;
    }
}
