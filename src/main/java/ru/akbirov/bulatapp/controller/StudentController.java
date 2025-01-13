package ru.akbirov.bulatapp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.akbirov.bulatapp.dto.AddStudentRequestDto;
import ru.akbirov.bulatapp.dto.AllStudentResponseDto;
import ru.akbirov.bulatapp.entity.Group;
import ru.akbirov.bulatapp.entity.Student;
import ru.akbirov.bulatapp.mapper.StudentMapper;
import ru.akbirov.bulatapp.service.GroupService;
import ru.akbirov.bulatapp.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("/students")
@Tag(name = "Управление студентами", description = "Операции, связанные с управлением студентами")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private GroupService groupService;

    @PostMapping
    @Operation(summary = "Добавить нового студента",
            description = "Создает нового студента и возвращает информацию о нем")
    public ResponseEntity<AddStudentRequestDto> add(@RequestBody AddStudentRequestDto addStudentRequestDto) {

        Student student = studentMapper.toEntity(addStudentRequestDto);

        Group group = groupService.findById(addStudentRequestDto.getGroup().getId());
        student.setGroup(group);

        studentService.add(student);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(addStudentRequestDto);
    }

    @GetMapping
    @Operation(summary = "Получить всех студентов", description = "Получает список всех студентов")
    public ResponseEntity<List<AllStudentResponseDto>> getAllStudents() {
        List<AllStudentResponseDto> allStudentResponseDtos = studentService.getAllStudentsAsDto();
        return ResponseEntity.ok(allStudentResponseDtos);
    }
}
