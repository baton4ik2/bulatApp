package ru.akbirov.bulatapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import ru.akbirov.bulatapp.dto.JwtRequestDto;
import ru.akbirov.bulatapp.dto.JwtResponseDto;
import ru.akbirov.bulatapp.dto.RegistrationUserDto;
import ru.akbirov.bulatapp.dto.UserDto;
import ru.akbirov.bulatapp.entity.User;
import ru.akbirov.bulatapp.service.AuthService;
import ru.akbirov.bulatapp.util.JwtTokenUtils;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = AuthController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthService authService;

    @MockBean
    private JwtTokenUtils jwtTokenUtils;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testCreateAuthToken_Success() throws Exception {
        // Arrange
        JwtRequestDto jwtRequestDto = new JwtRequestDto();
        jwtRequestDto.setUsername("user");
        jwtRequestDto.setPassword("pass");
        String expectedToken = "Token";

        ResponseEntity responseEntity = new ResponseEntity<>(expectedToken, HttpStatus.OK);
        when(authService.createAuthToken(any(JwtRequestDto.class))).thenReturn(responseEntity);

        // Act & Assert
        mockMvc.perform(post("/auth")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(jwtRequestDto)))
                .andExpect(status().isOk())
                .andExpect(content().string("Token"));
    }

    @Test
    public void testCreateNewUser_Success() throws Exception {
        // Arrange
        RegistrationUserDto registrationUserDto = new RegistrationUserDto();
        registrationUserDto.setUsername("newuser");
        registrationUserDto.setPassword("newpass");

        ResponseEntity responseEntity = new ResponseEntity<>("User created", HttpStatus.CREATED);
        when(authService.createNewUser(any(RegistrationUserDto.class))).thenReturn(responseEntity);

        // Act & Assert
        mockMvc.perform(post("/registration")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(registrationUserDto))) // Пример JSON
                .andExpect(status().isCreated())
                .andExpect(content().string("User created"));
    }

    @Test
    public void testAdminData() throws Exception {
        // Act & Assert
        mockMvc.perform(get("/admin"))
                .andExpect(status().isOk())
                .andExpect(content().string("Admin data"));
    }
}
