package com.ramkiopt.main.services.security;

import com.ramkiopt.main.dto.UsersDto;
import com.ramkiopt.main.entities.Users;
import com.ramkiopt.main.repositories.UsersRepository;
import com.ramkiopt.main.services.app.users.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private UsersService<UsersDto> usersService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Users user = usersRepository.findByEmail(email);
        if (user != null) {
            List<GrantedAuthority> authorities = getUserAuthority(usersService.getRoles(email));
            return buildUserForAuthentication(user, authorities);
        } else {
            throw new UsernameNotFoundException("username not found");
        }
    }

    private List<GrantedAuthority> getUserAuthority(List<String> roles) {
        return roles
                .stream()
                .collect(ArrayList::new, (result, item) -> result.add(new SimpleGrantedAuthority(item)),
                        ArrayList::addAll);
    }

    private UserDetails buildUserForAuthentication(Users user, List<GrantedAuthority> authorities) {
        return new User(user.getEmail(), user.getPasswordEncrypted() == null ? "" : user.getPasswordEncrypted(),
                authorities);
    }
}