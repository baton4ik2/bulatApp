package ru.akbirov.bulatapp.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import ru.akbirov.bulatapp.dto.JwtRequestDto;
import ru.akbirov.bulatapp.dto.JwtResponseDto;
import ru.akbirov.bulatapp.dto.RegistrationUserDto;

@Service
public interface AuthService {

    ResponseEntity<JwtResponseDto> createAuthToken(JwtRequestDto jwtRequestDto);

    ResponseEntity<?> createNewUser(RegistrationUserDto registrationUserDto);

}
