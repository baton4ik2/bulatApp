package ru.akbirov.bulatapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.akbirov.bulatapp.dto.AddGroupRequestDto;

import ru.akbirov.bulatapp.dto.AllGroupResponseDto;
import ru.akbirov.bulatapp.dto.GroupResponseDto;
import ru.akbirov.bulatapp.dto.GroupUpdateRequestDto;
import ru.akbirov.bulatapp.entity.Group;

import ru.akbirov.bulatapp.exception.GroupNotFoundException;
import ru.akbirov.bulatapp.mapper.GroupMapper;

import ru.akbirov.bulatapp.service.GroupService;
import ru.akbirov.bulatapp.util.JwtTokenUtils;


import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = GroupController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class GroupControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GroupService groupService;

    @MockBean
    private GroupMapper groupMapper;

    @MockBean
    private JwtTokenUtils jwtTokenUtils;

    @Autowired
    private ObjectMapper objectMapper;


    private Group group;
    private AddGroupRequestDto addGroupRequestDto;
    private GroupResponseDto groupResponseDto;
    private AllGroupResponseDto allGroupResponseDto;
    private GroupUpdateRequestDto groupUpdateRequestDto;
    List<AllGroupResponseDto> groups;


    @BeforeEach
    void setUp() {

        group = Group.builder().id(1).groupNumber("it404").build();
        addGroupRequestDto = new AddGroupRequestDto();
        addGroupRequestDto.setGroupNumber("it404");
        groupResponseDto = new GroupResponseDto();
        groupResponseDto.setGroupNumber("it404");
        allGroupResponseDto = new AllGroupResponseDto();
        allGroupResponseDto.setGroupNumber("it404");
        groups = List.of(allGroupResponseDto);
        groupUpdateRequestDto = new GroupUpdateRequestDto();
        groupUpdateRequestDto.setGroupNumber("it404");

    }

    @Test
    void GroupController_AddGroup() throws Exception {

        when(groupMapper.toEntity(addGroupRequestDto)).thenReturn(group);
        doNothing().when(groupService).add(group);

        mockMvc.perform(post("/groups")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(addGroupRequestDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.groupNumber").value(addGroupRequestDto.getGroupNumber()));
        verify(groupService, times(1)).add(group);
    }

    @Test
    void GroupController_GetGroupByID() throws Exception {
        when(groupService.findById(group.getId())).thenReturn(group);
        when(groupMapper.toResponseDto(group)).thenReturn(groupResponseDto);

        mockMvc.perform(get("/groups/{groupId}", group.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(groupResponseDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.groupNumber").value(groupResponseDto.getGroupNumber()));
        verify(groupService, times(1)).findById(group.getId());
    }

    @Test
    void GroupController_GetGroupById_GroupNotFound() throws Exception {
        when(groupService.findById(group.getId())).thenThrow(GroupNotFoundException.class);

        mockMvc.perform(get("/groups/{groupId}", group.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(groupResponseDto)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("Group not found")));
        verify(groupService, times(1)).findById(group.getId());
    }

    @Test
    void GroupController_GetAllGroups_ReturnAllGroupResponseDto() throws Exception {

        when(groupService.getAllGroupsAsDto()).thenReturn(groups);

        mockMvc.perform(get("/groups")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(groups)))
                .andExpect(jsonPath("$[0].groupNumber").value(group.getGroupNumber()));
        verify(groupService, times(1)).getAllGroupsAsDto();
    }

    @Test
    void GroupController_GetAllGroups_ReturnEmptyList() throws Exception {

        List<AllGroupResponseDto> groups = Collections.emptyList();
        when(groupService.getAllGroupsAsDto()).thenReturn(groups);

        mockMvc.perform(get("/groups")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[]")); // Проверка, что возвращается пустой список

        verify(groupService, times(1)).getAllGroupsAsDto();
    }

    @Test
    void GroupController_UpdateGroup() throws Exception {
        when(groupMapper.toEntity(groupUpdateRequestDto)).thenReturn(group);
        doNothing().when(groupService).update(group);

        mockMvc.perform(put("/groups")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(groupUpdateRequestDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.groupNumber").value(groupUpdateRequestDto.getGroupNumber()));
        verify(groupService, times(1)).update(group);
    }


}