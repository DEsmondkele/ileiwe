package com.ileiwe.data.controller;

import com.ileiwe.data.dto.InstructorPartyDto;
import com.ileiwe.data.dto.StudentPartyDto;
import com.ileiwe.data.model.Instructor;
import com.ileiwe.data.model.LearningParty;
import com.ileiwe.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.LoginException;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api")

public class RegistrationController {
    @Autowired
    private InstructorServiceImpl instructorService;
    @Autowired
    private StudentServiceImpl studentService;

    @PostMapping("/instructor")
    public ResponseEntity<?> registerAsInstructor(@RequestBody InstructorPartyDto instructorPartyDto) {
        log.info("instructor object--> {}", instructorPartyDto);
        try {
            return ResponseEntity.ok().body(instructorService.save(instructorPartyDto));
        } catch (UserAlreadyExistException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PostMapping("/student")
    public ResponseEntity<?> registerAsStudent(@RequestBody StudentPartyDto studentPartyDto){
        log.info("student object-->{}", studentPartyDto);
        return  ResponseEntity.ok().body(studentService.save(studentPartyDto));
    }






//    @RequestMapping("/login")
//    public LearningParty loginStudent(@RequestBody StudentPartyDto studentPartyDto) throws LoginException {
//       log.info("loggers details-->{}",studentPartyDto);
//        return studentService.login(studentPartyDto);
//    }

}
