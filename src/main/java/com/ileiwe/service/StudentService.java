package com.ileiwe.service;


import com.ileiwe.data.dto.StudentPartyDto;
import com.ileiwe.data.model.Course;
import com.ileiwe.data.model.Student;

import java.util.Optional;


public interface StudentService {

    Student save(StudentPartyDto dto);
    Optional<Course> viewCourse(Long id);

   // LearningParty login(StudentPartyDto studentPartyDto) throws LoginException;
}
