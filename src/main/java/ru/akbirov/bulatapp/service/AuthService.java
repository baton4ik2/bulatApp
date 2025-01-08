package ru.akbirov.bulatapp.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import ru.akbirov.bulatapp.dto.JwtRequestDto;
import ru.akbirov.bulatapp.dto.RegistrationUserDto;

@Service
public interface AuthService {

    ResponseEntity<?> createAuthToken(@RequestBody JwtRequestDto jwtRequestDto);

    ResponseEntity<?> createNewUser(@RequestBody RegistrationUserDto registrationUserDto);

}
