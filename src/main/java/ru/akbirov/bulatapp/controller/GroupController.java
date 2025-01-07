package ru.akbirov.bulatapp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.akbirov.bulatapp.dto.AddGroupRequestDto;
import ru.akbirov.bulatapp.dto.AllGroupResponseDto;
import ru.akbirov.bulatapp.dto.GroupResponseDto;
import ru.akbirov.bulatapp.dto.GroupUpdateRequestDto;
import ru.akbirov.bulatapp.entity.Group;
import ru.akbirov.bulatapp.mapper.GroupMapper;
import ru.akbirov.bulatapp.service.GroupService;

import java.util.List;

@RestController
@RequestMapping("/groups")
@Tag(name = "Управление группами", description = "Операции, связанные с управлением группами")
public class GroupController {

    @Autowired
    private GroupService groupService;

    @Autowired
    private GroupMapper groupMapper;


    @PostMapping
    @Operation(summary = "Добавить новую группу", description = "Создает новую группу и возвращает созданную группу")
    public ResponseEntity<AddGroupRequestDto> addGroup(@RequestBody AddGroupRequestDto addGroupRequestDto) {

        Group group = groupMapper.toEntity(addGroupRequestDto);

        groupService.add(group);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(addGroupRequestDto);
    }

    @GetMapping("/{groupId}")
    @Operation(summary = "Получить группу по ID", description = "Получает группу по ее ID")
    public ResponseEntity<GroupResponseDto> getGroup(@PathVariable int groupId) {

        Group group = groupService.findById(groupId);

        GroupResponseDto groupResponseDto = groupMapper.toResponseDto(group);

        return ResponseEntity.ok(groupResponseDto);
    }

    @GetMapping
    @Operation(summary = "Получить все группы", description = "Получает список всех групп")
    public ResponseEntity<List<AllGroupResponseDto>> getAllGroups() {
        List<AllGroupResponseDto> allGroupResponseDtos = groupService.getAllGroupsAsDto();
        return ResponseEntity.ok(allGroupResponseDtos);
    }

    @PutMapping
    @Operation(summary = "Обновить существующую группу", description = "Обновляет данные существующей группы")
    public ResponseEntity<GroupUpdateRequestDto> updateGroup(@RequestBody GroupUpdateRequestDto updateRequestDto) {
        Group group = groupMapper.toEntity(updateRequestDto);
        groupService.update(group);
        return ResponseEntity.ok(updateRequestDto);
    }

    @GetMapping("/test")
    @Operation(summary = "Тестовый эндпоинт для группы", description = "Тестовый эндпоинт")
    public ResponseEntity<Group> test(@RequestBody Group group) {
        groupService.test(group);
        return ResponseEntity.ok(group);
    }
}
