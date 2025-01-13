package ru.akbirov.bulatapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.akbirov.bulatapp.dto.AddStudentRequestDto;
import ru.akbirov.bulatapp.dto.AllStudentResponseDto;
import ru.akbirov.bulatapp.dto.GroupIdDto;
import ru.akbirov.bulatapp.entity.Group;
import ru.akbirov.bulatapp.entity.Student;
import ru.akbirov.bulatapp.mapper.StudentMapper;
import ru.akbirov.bulatapp.service.GroupService;
import ru.akbirov.bulatapp.service.StudentService;
import ru.akbirov.bulatapp.util.JwtTokenUtils;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = StudentController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;

    @MockBean
    private GroupService groupService;

    @MockBean
    private StudentMapper studentMapper;

    @MockBean
    private JwtTokenUtils jwtTokenUtils;

    @Autowired
    private ObjectMapper objectMapper;

    private Student student;
    private Student student2;
    private Group group;
    private List<AllStudentResponseDto> students;
    private AddStudentRequestDto addStudentRequestDto;
    private AllStudentResponseDto allStudentResponseDto;
    private GroupIdDto groupIdDto;

    @BeforeEach
    void setUp() {
        student = Student.builder().surname("Akbirov").build();
        student2 = Student.builder().surname("Ivanov").build();
        group = Group.builder().groupNumber("it404").build();
        addStudentRequestDto = new AddStudentRequestDto();
        allStudentResponseDto = new AllStudentResponseDto();
        groupIdDto = new GroupIdDto(1);
        addStudentRequestDto.setSurname("Akbirov");
        addStudentRequestDto.setGroup(groupIdDto);
        allStudentResponseDto.setSurname("Akbirov");
        students = List.of(allStudentResponseDto);
    }

    @Test
    public void StudentController_addStudent_ReturnAddStudentRequestDto() throws Exception {

        // Настраиваем моки
        when(studentMapper.toEntity(addStudentRequestDto)).thenReturn(student);
        when(groupService.findById(addStudentRequestDto.getGroup().getId())).thenReturn(group);
        doNothing().when(studentService).add(student);

        // Выполняем запрос и проверяем результат
        mockMvc.perform(post("/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(addStudentRequestDto)))
                .andExpect(status().isCreated())
                .andExpect(content().json(objectMapper.writeValueAsString(addStudentRequestDto)));
        verify(studentService, times(1)).add(student);
    }

    @Test
    void StudentController_GetAllStudent_ReturnAllStudentResponseDto() throws Exception {

        when(studentService.getAllStudentsAsDto()).thenReturn(students);

        mockMvc.perform(get("/students")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(students)));
        verify(studentService, times(1)).getAllStudentsAsDto();
    }


}
