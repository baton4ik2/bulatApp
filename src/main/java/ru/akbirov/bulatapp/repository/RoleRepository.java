package ru.akbirov.bulatapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.akbirov.bulatapp.entity.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(String name);
}
