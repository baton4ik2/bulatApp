package ru.akbirov.bulatapp.service;

import org.springframework.stereotype.Service;
import ru.akbirov.bulatapp.dto.AllGroupResponseDto;
import ru.akbirov.bulatapp.entity.Group;

import java.util.List;
import java.util.Optional;

@Service
public interface GroupService {

    void add(Group group);

    Group findById(int id);

    List<Group> findAll();

    void update(Group group);

    void deleteById(int id);

    List<AllGroupResponseDto> getAllGroupsAsDto();

    void test(Group group);
}
