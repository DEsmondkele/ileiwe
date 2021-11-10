package com.ileiwe.service;

import com.ileiwe.data.dto.CourseDto;
import com.ileiwe.data.dto.InstructorPartyDto;
import com.ileiwe.data.model.Course;
import com.ileiwe.data.model.Instructor;

import java.util.Optional;

public interface InstructorService {
    Instructor save(InstructorPartyDto dto) throws UserAlreadyExistException;
    Optional<Instructor> findById(InstructorPartyDto instructorPartyDto);

    void deleteCourse(Long id);
   Optional<Course> findById(Long id,CourseDto courseDto);
}
