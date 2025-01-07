package ru.akbirov.bulatapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.akbirov.bulatapp.entity.Group;

@Repository
public interface GroupRepository extends JpaRepository<Group, Integer> {

}
