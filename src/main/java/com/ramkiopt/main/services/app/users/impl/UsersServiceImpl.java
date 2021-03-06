package com.ramkiopt.main.services.app.users.impl;

import com.ramkiopt.main.dto.UsersDto;
import com.ramkiopt.main.entities.Users;
import com.ramkiopt.main.repositories.UsersOnRolesCriteriaRepository;
import com.ramkiopt.main.repositories.UsersRepository;
import com.ramkiopt.main.services.app.base.BaseServiceAbstract;
import com.ramkiopt.main.services.app.base.RowStatus;
import com.ramkiopt.main.services.app.users.UsersService;
import com.ramkiopt.main.services.security.UserRole;
import com.ramkiopt.main.services.utils.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersServiceImpl extends BaseServiceAbstract<Users, UsersDto> implements UsersService<UsersDto> {

    private final Logger LOGGER = LoggerFactory.getLogger(UsersServiceImpl.class);
    private final UsersRepository usersRepository;
    private final UsersOnRolesCriteriaRepository usersOnRolesCriteriaRepository;

    public UsersServiceImpl(UsersRepository usersRepository,
                            UsersOnRolesCriteriaRepository usersOnRolesCriteriaRepository) {
        this.usersRepository = usersRepository;
        this.usersOnRolesCriteriaRepository = usersOnRolesCriteriaRepository;
        setJpaRepository(usersRepository);
    }

    @Override
    protected void setJpaRepository(JpaRepository<Users, Long> jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public UsersDto create(UsersDto dto) {
        dto.setStatus(RowStatus.ENABLE);
        return createInDb(new Users(), dto);
    }

    @Override
    public List<UsersDto> createAll(List<UsersDto> dtos) {
        return null;
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

    @Override
    public boolean deleteInDb(Long id) {
        Users users = jpaRepository.getOne(id);
        if (users == null) {
            return false;
        }
        users.setStatus(RowStatus.DELETED);
        jpaRepository.save(users);
        return true;
    }

    @Override
    public UsersDto getByEmail(String email) {
        Users users = usersRepository.findByEmail(email);
        if (users == null) {
            return null;
        }
        UsersDto usersDto = new UsersDto();
        ObjectMapper.mapCustom(users, usersDto);
        return usersDto;
    }

    @Override
    public List<String> getRoles(String username) {
        return usersOnRolesCriteriaRepository.getRoles(username, 0, UserRole.values().length);
    }
}
