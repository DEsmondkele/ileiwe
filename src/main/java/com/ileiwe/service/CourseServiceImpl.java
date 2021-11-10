package com.ileiwe.service;

import com.ileiwe.data.dto.CourseDto;
import com.ileiwe.data.model.Course;
import com.ileiwe.data.repository.CourseRepository;
import com.ileiwe.exceptions.CourseDoesNotExitException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService{
    @Autowired
    CourseRepository courseRepository;


    @Override
    public Course saveCourse(CourseDto courseDto) {
        if (courseDto == null){
            throw new IllegalArgumentException("course cannot be null");
        }
        Course newCourse = Course.builder()
                .title(courseDto.getTitle()).language(courseDto.getLanguage())
                .description(courseDto.getDescription())
                .datePublished(courseDto.getDatePublished())
                .dateCreated(courseDto.getDateCreated()).build();
        return courseRepository.save(newCourse);
    }

    @Override
    public Course updateCourse(CourseDto courseDto) {
        Course course = new Course();
        if(courseDto ==null){
            throw  new IllegalArgumentException("Course does not exist");
        }
        Optional<Course> result = courseRepository.findById(course.getId());
        if(result.isPresent()){
            course = Course.builder()
                    .updatedAt(courseDto.getDateUpdated())
                    .description(courseDto.getDescription())
                    .title(courseDto.getTitle())
                    .build();
            return courseRepository.save(course);
        }
        else{
            throw new CourseDoesNotExitException("Course with the does not exist");
        }
    }
}
