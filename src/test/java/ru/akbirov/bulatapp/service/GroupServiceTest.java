package ru.akbirov.bulatapp.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.akbirov.bulatapp.dto.AllGroupResponseDto;
import ru.akbirov.bulatapp.dto.AllStudentResponseDto;
import ru.akbirov.bulatapp.entity.Group;
import ru.akbirov.bulatapp.entity.Student;
import ru.akbirov.bulatapp.exception.GroupNotFoundException;
import ru.akbirov.bulatapp.mapper.GroupMapper;
import ru.akbirov.bulatapp.mapper.StudentMapper;
import ru.akbirov.bulatapp.repository.GroupRepository;
import ru.akbirov.bulatapp.repository.StudentRepository;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GroupServiceTest {

    @Mock
    private GroupRepository groupRepository;

    @Mock
    private GroupMapper groupMapper;

    @InjectMocks
    private GroupServiceImpl groupService;

    private Group group;
    private Group group2;
    private List<Group> groups;

    @BeforeEach
    public void setUp() {
        group = Group.builder()
                .id(1)
                .groupNumber("it404")
                .build();

        group2 = Group.builder()
                .id(2)
                .groupNumber("sale101")
                .build();

        groups = Arrays.asList(group, group2);


    }

    @Test
    void GroupService_AddGroup() {
        // Arrange
        // Act
        groupService.add(group);

        // Assert
        verify(groupRepository, times(1)).save(group);
    }

    @Test
    void GroupService_FindById_ReturnGroup() {
        // Arrange
        when(groupRepository.findById(1)).thenReturn(Optional.ofNullable(group));

        // Act
        Group result = groupService.findById(1);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("it404", result.getGroupNumber());
        verify(groupRepository, times(1)).findById(1);
    }

    @Test
    void GroupService_FindById_GroupNotFoundException() {
        // Arrange
        when(groupRepository.findById(3)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(GroupNotFoundException.class, () -> groupService.findById(3));
        verify(groupRepository, times(1)).findById(3);
        verify(groupRepository, times(0)).save(Mockito.any(Group.class));
    }

    @Test
    void GroupService_findAll_ReturnGroups() {
        // Arrange
        when(groupRepository.findAll()).thenReturn(groups);

        // Act
        List<Group> result = groupService.findAll();

        // Assert
        assertNotNull(result);
        assertEquals(groups.size(), result.size());
        verify(groupRepository, times(1)).findAll();
    }

    @Test
    void GroupService_updateGroup() {
        // Arrange
        when(groupRepository.findById(1)).thenReturn(Optional.ofNullable(group));
        when(groupRepository.save(Mockito.any(Group.class))).thenReturn(group);

        // Act
        groupService.update(group);

        // Assert
        verify(groupRepository, times(1)).findById(1);
        verify(groupRepository, times(1)).save(group);
    }

    @Test
    void GroupService_updateGroup_GroupNotFoundException() {
        // Arrange
        when(groupRepository.findById(3)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(GroupNotFoundException.class, () -> groupService.findById(3));
        verify(groupRepository, times(1)).findById(3);
        verify(groupRepository, times(0)).save(Mockito.any(Group.class));
    }

    @Test
    void GroupService_deleteGroup() {
        // Arrange
        when(groupRepository.findById(1)).thenReturn(Optional.ofNullable(group));

        // Act
        groupService.deleteById(1);

        // Assert
        verify(groupRepository, times(1)).deleteById(1);
    }

    @Test
    void GroupService_deleteGroup_GroupNotFoundException() {
        // Arrange
        when(groupRepository.findById(3)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(GroupNotFoundException.class, () -> groupService.deleteById(3));
        verify(groupRepository, times(0)).deleteById(3);
    }

    @Test
    void GroupService_GetAllGroupsAsDto() {
        // Arrange
        AllGroupResponseDto groupDto1 = new AllGroupResponseDto();
        groupDto1.setGroupNumber("it104");

        AllGroupResponseDto groupDto2 = new AllGroupResponseDto();
        groupDto2.setGroupNumber("sale912");

        when(groupRepository.findAll()).thenReturn(groups);
        when(groupMapper.toAllGroupResponseDto(group)).thenReturn(groupDto1);
        when(groupMapper.toAllGroupResponseDto(group2)).thenReturn(groupDto2);

        // Act
        List<AllGroupResponseDto> result = groupService.getAllGroupsAsDto();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("it104", result.get(0).getGroupNumber());
        assertEquals("sale912", result.get(1).getGroupNumber());
        verify(groupRepository, times(1)).findAll();
        verify(groupMapper, times(1)).toAllGroupResponseDto(group);
        verify(groupMapper, times(1)).toAllGroupResponseDto(group2);
    }

    @Test
    void GroupService_GetAllGroupsAsDto_EmptyList() {
        // Arrange
        when(groupRepository.findAll()).thenReturn(Collections.emptyList());

        // Act
        List<AllGroupResponseDto> result = groupService.getAllGroupsAsDto();

        // Assert
        assertNotNull(result);
        assertEquals(0, result.size());
        verify(groupRepository, times(1)).findAll();
        verify(groupMapper, times(0)).toAllGroupResponseDto(Mockito.any());
    }

    @Test
    void GroupService_test() {
        // Arrange
        // Act
        groupService.test(group); 

        // Assert
        verify(groupRepository, times(1)).save(group);
        verify(groupRepository, times(1)).delete(group);
    }
}
// Arrange
// Act
// Assert
