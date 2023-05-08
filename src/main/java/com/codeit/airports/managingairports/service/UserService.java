package com.codeit.airports.managingairports.service;

import com.codeit.airports.managingairports.model.User;
import com.codeit.airports.managingairports.model.dto.UserRegisterDto;
import com.codeit.airports.managingairports.model.enumerations.Role;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface UserService extends UserDetailsService {

    User register(String username, String password, String repeatPassword, Role role);


    Optional<User> findUserByUsername(String username);

    User register(UserRegisterDto userRegisterDto);

    UserDetails loadUserByUsername(String username);
}
