package ru.akbirov.bulatapp.service;

import org.springframework.stereotype.Service;
import ru.akbirov.bulatapp.dto.AllGroupResponseDto;
import ru.akbirov.bulatapp.dto.AllStudentResponseDto;
import ru.akbirov.bulatapp.entity.Group;
import ru.akbirov.bulatapp.entity.Student;

import java.util.List;

@Service
public interface StudentService {

    void add(Student student);

    List<Student> findAll();

    List<AllStudentResponseDto> getAllStudentsAsDto();
}
