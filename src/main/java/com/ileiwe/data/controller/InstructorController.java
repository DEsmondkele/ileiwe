package com.ileiwe.data.controller;

import com.ileiwe.data.dto.CourseDto;
import com.ileiwe.data.dto.InstructorPartyDto;
import com.ileiwe.data.model.Course;
import com.ileiwe.data.model.Instructor;
import com.ileiwe.data.repository.CourseRepository;
import com.ileiwe.service.CourseServiceImpl;
import com.ileiwe.service.InstructorServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Slf4j
@Service
@RestController
@RequestMapping("/api")

public class InstructorController {
    @Autowired
    private InstructorServiceImpl instructorService;
    @Autowired
    private CourseServiceImpl courseService;



    @GetMapping("/instructor:{id}")
    public Optional<Instructor> findById(@RequestBody InstructorPartyDto instructorPartyDto){
        return  instructorService.findById(instructorPartyDto);
    }

    @PostMapping("/course")
    public  ResponseEntity<?> createCourse(@RequestBody CourseDto courseDto){
        log.info("course object-->{}",courseDto);
        return ResponseEntity.ok().body(courseService.saveCourse(courseDto));
    }
    @PatchMapping("/course:{id}")
    public  ResponseEntity<?> updateCourse(@RequestBody CourseDto courseDto){
        log.info("course object-->{}",courseDto);
        return  ResponseEntity.ok().body(courseService.updateCourse(courseDto));
    }
}
