package ru.akbirov.bulatapp.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.akbirov.bulatapp.dto.*;
import ru.akbirov.bulatapp.entity.Group;

@Mapper(componentModel = "spring")
public interface GroupMapper {

    Group toEntity(AddGroupRequestDto dto);

    GroupResponseDto toResponseDto(Group group);

    @Mapping(target = "quantity", expression = "java(group.getStudents().size())")
    AllGroupResponseDto toAllGroupResponseDto(Group group);

    Group toEntity(GroupUpdateRequestDto dto);
}
