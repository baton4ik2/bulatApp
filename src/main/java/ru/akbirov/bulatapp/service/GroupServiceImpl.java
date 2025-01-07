package ru.akbirov.bulatapp.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.akbirov.bulatapp.dto.AllGroupResponseDto;
import ru.akbirov.bulatapp.entity.Group;
import ru.akbirov.bulatapp.exception.GroupNotFoundException;
import ru.akbirov.bulatapp.mapper.GroupMapper;
import ru.akbirov.bulatapp.repository.GroupRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
//@Profile("prod")
@ConditionalOnProperty(value = "test.mode", havingValue = "false")
public class GroupServiceImpl implements GroupService {


    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private GroupMapper groupMapper;

    @Override
    public void add(Group group) {
        log.info("add group: {}", group);
        groupRepository.save(group);
    }

    @Override
    public Group findById(int id) {
        log.info("Find group by id: {} ", id);
        return groupRepository.findById(id).orElseThrow(GroupNotFoundException::new);
    }

    @Override
    public List<Group> findAll() {
        //System.out.println("Профиль prod");
        log.info("Find all groups");
        return groupRepository.findAll();
    }

    @Override
    public void update(Group group) {
        log.info("update group: {}", group);
        groupRepository.save(group);
    }

    @Override
    public void deleteById(int id) {
        log.info("Delete group by id: {}", id);
        groupRepository.deleteById(id);
    }

    @Override
    public List<AllGroupResponseDto> getAllGroupsAsDto() {
        log.info("Get all groups as dto");
        return groupRepository.findAll().stream()
                .map(groupMapper::toAllGroupResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void test(Group group) {
        groupRepository.save(group);
        //method();
        groupRepository.delete(group);
    }

    private void method() {
        throw new RuntimeException();
    }
}
