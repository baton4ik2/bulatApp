package ru.akbirov.bulatapp.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import ru.akbirov.bulatapp.dto.*;

import ru.akbirov.bulatapp.util.JwtTokenUtils;

import java.util.ArrayList;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {

    @InjectMocks
    private AuthServiceImpl authService;

    @Mock
    private UserService userService;

    @Mock
    private JwtTokenUtils jwtTokenUtils;

    @Mock
    private AuthenticationManager authenticationManager;

    @Test
    public void AuthService_CreateAuthToken_ReturnsToken() {
        // Arrange
        JwtRequestDto jwtRequestDto = new JwtRequestDto("username", "password");
        UserDetails userDetails = new User("username", "password", new ArrayList<>());
        String token = "generatedToken";

        when(authenticationManager.authenticate(any())).thenReturn(new UsernamePasswordAuthenticationToken(userDetails, null));
        when(userService.loadUserByUsername("username")).thenReturn(userDetails);
        when(jwtTokenUtils.generateToken(userDetails)).thenReturn(token);

        // Act
        ResponseEntity<?> response = authService.createAuthToken(jwtRequestDto);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(token, ((JwtResponseDto) response.getBody()).getToken());
    }

    @Test
    public void AuthService_CreateNewUser_ReturnsBadRequest() {
        // Arrange
        RegistrationUserDto registrationUserDto =
                new RegistrationUserDto("username", "password",
                        "differentPassword", "email@example.com");

        // Act
        ResponseEntity<?> response = authService.createNewUser(registrationUserDto);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Пароли не совпадают", ((AppErrorDto) response.getBody()).getMessage());
    }

    @Test
    public void AuthService_CreateNewUser_ValidData() {
        // Arrange
        RegistrationUserDto registrationUserDto =
                new RegistrationUserDto("username", "password",
                        "password", "email@example.com");
        ru.akbirov.bulatapp.entity.User user = new ru.akbirov.bulatapp.entity.User(
                1, "username", "password", "email@example.com", new ArrayList<>());
        when(userService.createNewUser(registrationUserDto)).thenReturn(user);

        // Act
        ResponseEntity<?> response = authService.createNewUser(registrationUserDto);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        UserDto userDto = (UserDto) response.getBody();
        assertEquals(user.getId(), userDto.getId());
        assertEquals(user.getUsername(), userDto.getUsername());
        assertEquals(user.getEmail(), userDto.getEmail());
    }
}
