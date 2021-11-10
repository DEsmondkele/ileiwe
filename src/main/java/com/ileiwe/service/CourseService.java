package com.ileiwe.service;

import com.ileiwe.data.dto.CourseDto;
import com.ileiwe.data.model.Course;

public interface CourseService {
    Course saveCourse(CourseDto courseDto);
    Course updateCourse(CourseDto courseDto);
}
