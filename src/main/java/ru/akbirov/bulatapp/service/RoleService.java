package ru.akbirov.bulatapp.service;

import org.springframework.stereotype.Service;
import ru.akbirov.bulatapp.entity.Role;

@Service
public interface RoleService {

    Role getUserRole();
}
