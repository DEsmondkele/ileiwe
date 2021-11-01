package com.ileiwe.data.controller;

import com.ileiwe.data.dto.InstructorPartyDto;
import com.ileiwe.service.InstructorService;
import com.ileiwe.service.InstructorServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api")

public class RegistrationController {

     @Autowired
   private   InstructorServiceImpl instructorService;

    @PostMapping("/instructor")
    public ResponseEntity<?>registerAsInstructor(@RequestBody InstructorPartyDto instructorPartyDto){
      log.info("instructor object--> {}", instructorPartyDto);
       return ResponseEntity.ok().body(instructorService.save(instructorPartyDto));
    }
}
