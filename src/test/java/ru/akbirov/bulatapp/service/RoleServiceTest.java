package ru.akbirov.bulatapp.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.akbirov.bulatapp.entity.Role;
import ru.akbirov.bulatapp.repository.RoleRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RoleServiceTest {

    @InjectMocks
    private RoleServiceImpl roleService;

    @Mock
    private RoleRepository roleRepository;

    @Test
    public void RoleService_GetUserRole() {
        // Arrange
        Role expectedRole = new Role();
        expectedRole.setName("ROLE_USER");
        when(roleRepository.findByName("ROLE_USER")).thenReturn(Optional.of(expectedRole));

        // Act
        Role actualRole = roleService.getUserRole();

        // Assert
        assertNotNull(actualRole);
        assertEquals(expectedRole.getName(), actualRole.getName());
        verify(roleRepository, times(1)).findByName("ROLE_USER");
    }

    @Test
    public void RoleService_GetUserRole_RoleNotFound() {
        // Arrange
        when(roleRepository.findByName("ROLE_USER")).thenReturn(Optional.empty());

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            roleService.getUserRole();
        });

        assertEquals("Role not found", exception.getMessage());
        verify(roleRepository, times(1)).findByName("ROLE_USER");
    }
}
