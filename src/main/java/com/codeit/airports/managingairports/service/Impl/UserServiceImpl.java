package com.codeit.airports.managingairports.service.Impl;

import com.codeit.airports.managingairports.model.User;
import com.codeit.airports.managingairports.model.dto.UserRegisterDto;
import com.codeit.airports.managingairports.model.enumerations.Role;
import com.codeit.airports.managingairports.model.exceptions.InvalidUsernameOrPasswordException;
import com.codeit.airports.managingairports.model.exceptions.PasswordsDoNotMatchException;
import com.codeit.airports.managingairports.model.exceptions.UsernameAlreadyExistsException;
import com.codeit.airports.managingairports.repository.UserRepository;
import com.codeit.airports.managingairports.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;

    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepository.findByUsername(s).orElseThrow(() -> new UsernameNotFoundException(s));
    }

    @Override
    public Optional<User> findUserByUsername(String username) {
        return this.userRepository.findByUsername(username);
    }


    @Override
    public User register(String username, String password, String repeatPassword, Role role) {
        Pattern pattern = Pattern.compile("^(?=.{3,20}$)(?![_.])(?!.*[_.]{2})[a-zA-Z0-9._]+(?<![_.])$");
        Matcher matcher = pattern.matcher(username);
        if (!matcher.matches()) {
            throw new InvalidUsernameOrPasswordException();
        }
        if (username == null || username.isEmpty() || password == null || password.isEmpty())
            throw new InvalidUsernameOrPasswordException();
        if (!password.equals(repeatPassword))
            throw new PasswordsDoNotMatchException();
        if (this.userRepository.findByUsername(username).isPresent())
            throw new UsernameAlreadyExistsException(username);

        User user = new User(username, passwordEncoder.encode(password), role);


        return userRepository.save(user);
    }

    @Override
    public User register(UserRegisterDto userRegisterDto) {
        Pattern pattern = Pattern.compile("^(?=.{3,20}$)(?![_.])(?!.*[_.]{2})[a-zA-Z0-9._]+(?<![_.])$");
        Matcher matcher = pattern.matcher(userRegisterDto.getUsername());
        if (!matcher.matches()) {
            throw new InvalidUsernameOrPasswordException();
        }
        if (userRegisterDto.getUsername() == null || userRegisterDto.getUsername().isEmpty()
                || userRegisterDto.getPassword() == null || userRegisterDto.getPassword().isEmpty())
            throw new InvalidUsernameOrPasswordException();
        if (!userRegisterDto.getPassword().equals(userRegisterDto.getRepeatPassword()))
            throw new InvalidUsernameOrPasswordException();
        if (this.userRepository.findByUsername(userRegisterDto.getUsername()).isPresent())
            throw new InvalidUsernameOrPasswordException();

        User user = new User(userRegisterDto.getUsername(), passwordEncoder.encode(userRegisterDto.getPassword()), Role.ROLE_USER);


        return userRepository.save(user);


    }


}
