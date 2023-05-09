package com.codeit.airports.managingairports;

import com.codeit.airports.managingairports.model.User;
import com.codeit.airports.managingairports.model.dto.UserRegisterDto;
import com.codeit.airports.managingairports.model.enumerations.Role;
import com.codeit.airports.managingairports.model.exceptions.InvalidUsernameOrPasswordException;
import com.codeit.airports.managingairports.model.exceptions.PasswordsDoNotMatchException;
import com.codeit.airports.managingairports.repository.UserRepository;
import com.codeit.airports.managingairports.service.Impl.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;


    @Test(expected = UsernameNotFoundException.class)
    public void testLoadUserByUsernameThrowsException() {
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.empty());
        userService.loadUserByUsername("nonexistinguser");
    }


    @Test
    public void testFindUserByUsername() {
        User user = new User("john", "doe", Role.ROLE_USER);

        String username = "john";


        when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));

        Optional<User> result = userRepository.findByUsername(username);

        assertTrue(result.isPresent());
        assertEquals(result.get().getUsername(), username);
        verify(userRepository, Mockito.times(1)).findByUsername(username);


        verifyNoMoreInteractions(userRepository);
    }


    @Test
    public void testRegister() {
        // Set up input data
        String username = "john.doe";
        String password = "password";
        String repeatPassword = "password";
        Role role = Role.ROLE_USER;

        // Set up mock dependencies
        UserRepository userRepository = mock(UserRepository.class);
        PasswordEncoder passwordEncoder = mock(PasswordEncoder.class);

        // Set up expected output
        User expectedUser = new User(username, passwordEncoder.encode(password), role);
        when(userRepository.save(expectedUser)).thenReturn(expectedUser);

        // Set up test object
        UserServiceImpl userService = new UserServiceImpl(userRepository, passwordEncoder);

        // Call method under test
        User actualUser = userService.register(username, password, repeatPassword, role);

        // Verify the results
        assertEquals(expectedUser, actualUser);
        verify(userRepository).findByUsername(username);
        verify(userRepository).save(expectedUser);
        verifyNoMoreInteractions(userRepository);
    }


    @Test
    public void shouldThrowInvalidUsernameOrPasswordExceptionWhenUsernameIsInvalid() {
        UserRegisterDto userRegisterDto = new UserRegisterDto("t", "password", "password", Role.ROLE_USER);
        userRegisterDto.setUsername("t");
        userRegisterDto.setPassword("password");
        userRegisterDto.setRepeatPassword("password");

        assertThrows(InvalidUsernameOrPasswordException.class, () -> userService.register(userRegisterDto));
    }

    @Test
    public void shouldThrowInvalidUsernameOrPasswordExceptionWhenUsernameIsNull() {
        UserRegisterDto userRegisterDto = new UserRegisterDto("", "password", "password", Role.ROLE_USER);


        assertThrows(InvalidUsernameOrPasswordException.class, () -> userService.register(userRegisterDto));
    }

    @Test
    public void shouldThrowInvalidUsernameOrPasswordExceptionWhenPasswordIsNull() {
        UserRegisterDto userRegisterDto = new UserRegisterDto("testuser", null, "password", Role.ROLE_USER);


        assertThrows(InvalidUsernameOrPasswordException.class, () -> userService.register(userRegisterDto));
    }

    @Test
    public void shouldThrowPasswordsDoNotMatchExceptionWhenPasswordsDoNotMatch() {
        UserRegisterDto userRegisterDto = new UserRegisterDto("testuser15", "password1", "password2gfd", Role.ROLE_USER);


        assertThrows(PasswordsDoNotMatchException.class, () -> userService.register(userRegisterDto));
    }


}
