package com.ramkiopt.main.services.app.commons.impl;

import com.ramkiopt.main.dto.UsersDto;
import com.ramkiopt.main.entities.UsersOnRoles;
import com.ramkiopt.main.repositories.RolesRepository;
import com.ramkiopt.main.repositories.UsersOnRolesRepository;
import com.ramkiopt.main.services.app.base.RowStatus;
import com.ramkiopt.main.services.app.commons.UsersCustomizationService;
import com.ramkiopt.main.services.app.users.UsersService;
import com.ramkiopt.main.services.security.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class UsersCustomizationServiceImpl implements UsersCustomizationService {

    private final UsersService<UsersDto> usersService;
    private final UsersOnRolesRepository usersOnRolesRepository;
    private final RolesRepository rolesRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UsersCustomizationServiceImpl(UsersService<UsersDto> usersService,
                                         UsersOnRolesRepository usersOnRolesRepository,
                                         RolesRepository rolesRepository,
                                         PasswordEncoder passwordEncoder) {
        this.usersService = usersService;
        this.usersOnRolesRepository = usersOnRolesRepository;
        this.rolesRepository = rolesRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public UsersDto createUser(UsersDto dto) {
        dto.setRoles(dto.getRoles() == null ? Collections.singletonList(UserRole.USER) : dto.getRoles());
        dto.setPasswordEncrypted(dto.getPassword() == null ? null : passwordEncoder.encode(dto.getPassword()));
        dto = usersService.create(dto);
        createUsersOnRoles(getRolesIds(dto.getRoles()), dto.getId());
        return dto;
    }

    @Override
    public UsersDto readUser(Long id) {
        if (usersService.get(id) == null || usersService.get(id).getStatus().equals(RowStatus.DELETED)) {
            throw new EntityNotFoundException();
        }
        return usersService.get(id);
    }

    private List<Long> getRolesIds(List<UserRole> userRoles) {
        return userRoles.stream().collect(ArrayList::new,
                (list, item) -> list.add(rolesRepository.findByName(item.name()).getId()), ArrayList::addAll);
    }

    private List<UserRole> getRolesByIds(List<Long> ids) {
        return rolesRepository.findAllById(ids)
                .stream()
                .collect(ArrayList::new, (result, item) -> result.add(UserRole.valueOf(item.getName())),
                        ArrayList::addAll);
    }

    private void createUsersOnRoles(List<Long> rolesIds, Long userId) {
        usersOnRolesRepository.saveAll(rolesIds.parallelStream().collect(ArrayList::new,
                (list, roleId) -> list.add(createUsersOnRoles(roleId, userId)), ArrayList::addAll));
    }

    private UsersOnRoles createUsersOnRoles(Long roleId, Long userId) {
        UsersOnRoles usersOnRoles = new UsersOnRoles();
        usersOnRoles.setRoleId(roleId);
        usersOnRoles.setUserId(userId);
        return usersOnRoles;
    }


    @Override
    public UsersDto readUserByEmail(String email) {
        UsersDto usersDto = usersService.getByEmail(email);
        List<Long> rolesIds = usersOnRolesRepository.findAllByUserId(usersDto.getId())
                .stream()
                .collect(ArrayList::new, (result, item) -> result.add(item.getRoleId()), ArrayList::addAll);
        usersDto.setRoles(getRolesByIds(rolesIds));
        return usersDto;
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
