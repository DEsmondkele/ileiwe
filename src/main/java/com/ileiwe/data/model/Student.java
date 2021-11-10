package com.ileiwe.data.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.engine.internal.Cascade;

import javax.persistence.*;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private  String firstName;

    private  String lastName;

//    private  Integer age;

    private LocalDateTime dob;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @OneToOne(cascade={CascadeType.ALL})
    private  LearningParty learningParty;

    @ManyToMany
    private List<Course> courses;


}
