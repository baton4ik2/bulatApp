package ru.akbirov.bulatapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.akbirov.bulatapp.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
}
