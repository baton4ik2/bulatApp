package ru.akbirov.bulatapp.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.akbirov.bulatapp.dto.AllGroupResponseDto;
import ru.akbirov.bulatapp.dto.AllStudentResponseDto;
import ru.akbirov.bulatapp.entity.Student;
import ru.akbirov.bulatapp.exception.GroupNotFoundException;
import ru.akbirov.bulatapp.mapper.StudentMapper;
import ru.akbirov.bulatapp.repository.StudentRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public void add(Student student) {
        log.info("add student {}", student);
        studentRepository.save(student);
    }

    @Override
    public List<Student> findAll() {
        log.info("Find all students");
         return studentRepository.findAll();
    }

    @Override
    public List<AllStudentResponseDto> getAllStudentsAsDto() {
        log.info("Get all students as dto");
        return studentRepository.findAll().stream()
                .map(studentMapper::toAllStudentResponseDto)
                .collect(Collectors.toList());
    }
}
