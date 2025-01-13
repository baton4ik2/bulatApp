package ru.akbirov.bulatapp.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.akbirov.bulatapp.dto.AddStudentRequestDto;
import ru.akbirov.bulatapp.dto.AllStudentResponseDto;
import ru.akbirov.bulatapp.entity.Group;
import ru.akbirov.bulatapp.entity.Student;
import ru.akbirov.bulatapp.mapper.StudentMapper;
import ru.akbirov.bulatapp.repository.StudentRepository;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private StudentMapper studentMapper;

    @InjectMocks
    private StudentServiceImpl studentService;

    private Student student;
    private Student student2;
    private List<Student> students;


    @BeforeEach
    public void setUp() {
        student = Student.builder()
                .surname("Akbirov")
                .build();

        student2 = Student.builder()
                .surname("Ivanov")
                .build();

        students = Arrays.asList(student, student2);


    }

    @Test
    void StudentService_AddStudent() {
        // Arrange
        // Act
        studentService.add(student);

        // Assert
        verify(studentRepository, times(1)).save(student);
    }

    @Test
    void StudentService_FindAll() {
        // Arrange
        when(studentRepository.findAll()).thenReturn(students);
        // Act
        List<Student> studentList = studentService.findAll();

        // Assert
        assertNotNull(studentList);
        assertEquals(2, studentList.size());
        assertEquals("Akbirov", studentList.get(0).getSurname());
        assertEquals("Ivanov", studentList.get(1).getSurname());
        verify(studentRepository, times(1)).findAll();

    }

    @Test
    void StudentService_GetAllStudentsAsDto() {
        // Arrange
        AllStudentResponseDto studentDto1 = new AllStudentResponseDto();
        studentDto1.setSurname("Akbirov");

        AllStudentResponseDto studentDto2 = new AllStudentResponseDto();
        studentDto2.setSurname("Ivanov");

        when(studentRepository.findAll()).thenReturn(students);
        when(studentMapper.toAllStudentResponseDto(student)).thenReturn(studentDto1);
        when(studentMapper.toAllStudentResponseDto(student2)).thenReturn(studentDto2);

        // Act
        List<AllStudentResponseDto> result = studentService.getAllStudentsAsDto();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Akbirov", result.get(0).getSurname());
        assertEquals("Ivanov", result.get(1).getSurname());
        verify(studentRepository, times(1)).findAll();
        verify(studentMapper, times(1)).toAllStudentResponseDto(student);
        verify(studentMapper, times(1)).toAllStudentResponseDto(student2);
    }


}
