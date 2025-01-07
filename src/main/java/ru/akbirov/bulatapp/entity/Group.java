package ru.akbirov.bulatapp.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "university_groups")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "group_number")
    private String groupNumber;

    @OneToMany(mappedBy = "group")
    private List<Student> students;


}
