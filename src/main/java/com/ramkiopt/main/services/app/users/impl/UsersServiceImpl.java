package com.ramkiopt.main.services.app.users.impl;

import com.ramkiopt.main.dto.UsersDto;
import com.ramkiopt.main.entities.Users;
import com.ramkiopt.main.repositories.UsersRepository;
import com.ramkiopt.main.services.app.base.BaseServiceAbstract;
import com.ramkiopt.main.services.app.users.UsersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.lang.reflect.InvocationTargetException;

@Service
public class UsersServiceImpl extends BaseServiceAbstract<Users, Long>
        implements UsersService<UsersDto> {

    private final Logger LOGGER = LoggerFactory.getLogger(UsersServiceImpl.class);
    @Autowired
    private UsersRepository usersRepository;

    @PostConstruct
    public void init() {
        setJpaRepository(usersRepository);
        setClass(Users.class);
        setClassDto(UsersDto.class);
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
    protected void setClassDto(Class dtoClass) {
        this.dtoClass = dtoClass;
    }

    @Override
    public Boolean create(UsersDto dto) {
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
    public UsersDto get(Long id) {
        return null;
    }

    @Override
    public Boolean update(Long id, UsersDto dto) {
        return null;
    }

    @Override
    public Boolean delete(Long id) {
        return null;
    }
}
