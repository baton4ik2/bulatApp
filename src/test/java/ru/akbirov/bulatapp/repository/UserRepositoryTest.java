package ru.akbirov.bulatapp.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.akbirov.bulatapp.entity.User;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        // Создание и сохранение тестовых данных
        User user1 = new User();
        user1.setId(1);
        user1.setUsername("testuser");
        user1.setPassword("password");
        userRepository.save(user1);

        User user2 = new User();
        user2.setId(2);
        user2.setUsername("anotheruser");
        user2.setPassword("password");
        userRepository.save(user2);
    }

    @Test
    public void shouldFindUserByUsername_WhenUserExists() {
        // Act
        Optional<User> foundUser = userRepository.findByUsername("testuser");

        // Assert
        assertThat(foundUser).isPresent();
        assertThat(foundUser.get().getUsername()).isEqualTo("testuser");
    }

    @Test
    public void shouldNotFindUserByUsername_WhenUserDoesNotExist() {
        // Act
        Optional<User> foundUser = userRepository.findByUsername("nonexistentuser");

        // Assert
        assertThat(foundUser).isNotPresent();
    }
}
