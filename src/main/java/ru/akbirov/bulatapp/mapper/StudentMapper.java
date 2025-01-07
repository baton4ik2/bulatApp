package ru.akbirov.bulatapp.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.akbirov.bulatapp.dto.AddStudentRequestDto;
import ru.akbirov.bulatapp.dto.AllStudentResponseDto;
import ru.akbirov.bulatapp.entity.Student;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    Student toEntity(AddStudentRequestDto dto);

    @Mapping(target = "group.id", source = "student.group.id")
    AllStudentResponseDto toAllStudentResponseDto(Student student);
}
