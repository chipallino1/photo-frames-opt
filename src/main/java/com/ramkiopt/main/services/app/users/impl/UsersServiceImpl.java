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

@Service
public class UsersServiceImpl extends BaseServiceAbstract<Users, UsersDto>
        implements UsersService<UsersDto> {

    private final Logger LOGGER = LoggerFactory.getLogger(UsersServiceImpl.class);
    @Autowired
    private UsersRepository usersRepository;

    @PostConstruct
    public void init() {
        setJpaRepository(usersRepository);
    }

    @Override
    protected void setJpaRepository(JpaRepository<Users, Long> jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public UsersDto create(UsersDto dto) {
        return createInDb(new Users(), dto);
    }

    @Override
    public UsersDto get(Long id) {
        return readFromDb(id, new UsersDto());
    }

    @Override
    public UsersDto update(Long id, UsersDto dto) {
        return updateInDb(id, dto);
    }

    @Override
    public Boolean delete(Long id) {
        return deleteInDb(id);
    }
}
