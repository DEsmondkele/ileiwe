package com.ileiwe.data.controller;

import com.ileiwe.data.dto.StudentPartyDto;
import com.ileiwe.service.StudentServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Service
@RestController
@RequestMapping("/api")

public class StudentController {

    @Autowired
    private StudentServiceImpl studentService;


}
