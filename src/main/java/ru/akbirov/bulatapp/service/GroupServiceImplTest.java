package ru.akbirov.bulatapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import ru.akbirov.bulatapp.dto.AllGroupResponseDto;
import ru.akbirov.bulatapp.entity.Group;
import ru.akbirov.bulatapp.exception.GroupNotFoundException;
import ru.akbirov.bulatapp.repository.GroupRepository;

import java.util.List;
import java.util.Optional;

@Service
//@Profile("test")
@ConditionalOnProperty(value = "test.mode", havingValue = "true")
public class GroupServiceImplTest implements GroupService {

    @Autowired
    private GroupRepository groupRepository;

    @Override
    public void add(Group group) {
        groupRepository.save(group);
    }

    @Override
    public Group findById(int id) {
        return groupRepository.findById(id).orElseThrow(() -> new GroupNotFoundException());
    }

    @Override
    public List<Group> findAll() {
        System.out.println("Профиль test");
        return groupRepository.findAll();
    }

    @Override
    public void update(Group group) {
        groupRepository.save(group);
    }

    @Override
    public void deleteById(int id) {
        groupRepository.deleteById(id);
    }

    @Override
    public List<AllGroupResponseDto> getAllGroupsAsDto() {
        return List.of();
    }

    @Override
    public void test(Group group) {

    }
}
