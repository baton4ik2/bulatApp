package ru.akbirov.bulatapp.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import ru.akbirov.bulatapp.dto.RegistrationUserDto;
import ru.akbirov.bulatapp.entity.User;

@Service
public interface UserService extends UserDetailsService {

    UserDetails loadUserByUsername(String username);

    User createNewUser(RegistrationUserDto registrationUserDto);
}
