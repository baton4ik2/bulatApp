package ru.akbirov.bulatapp.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.akbirov.bulatapp.entity.Role;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class RoleRepositoryTest {

    @Autowired
    private RoleRepository roleRepository;

    @BeforeEach
    public void setUp() {
        // Создание и сохранение тестовых данных
        Role adminRole = new Role();
        adminRole.setId(1);
        adminRole.setName("ROLE_ADMIN");
        roleRepository.save(adminRole);

        Role userRole = new Role();
        userRole.setId(2);
        userRole.setName("ROLE_USER");
        roleRepository.save(userRole);
    }

    @Test
    public void shouldFindRoleByName_WhenRoleExists() {
        // Act
        Optional<Role> foundRole = roleRepository.findByName("ROLE_ADMIN");

        // Assert
        assertThat(foundRole).isPresent();
        assertThat(foundRole.get().getName()).isEqualTo("ROLE_ADMIN");
    }

    @Test
    public void shouldNotFindRoleByName_WhenRoleDoesNotExist() {
        // Act
        Optional<Role> foundRole = roleRepository.findByName("ROLE_NON_EXISTENT");

        // Assert
        assertThat(foundRole).isNotPresent();
    }
}
