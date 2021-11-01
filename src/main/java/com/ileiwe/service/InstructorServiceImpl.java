package com.ileiwe.service;

import com.ileiwe.data.dto.InstructorPartyDto;
import com.ileiwe.data.model.Authority;
import com.ileiwe.data.model.Instructor;
import com.ileiwe.data.model.LearningParty;
import com.ileiwe.data.model.Role;
import com.ileiwe.data.repository.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class InstructorServiceImpl implements InstructorService{
    @Autowired
    InstructorRepository instructorRepository;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;


    @Override
    public Instructor save(InstructorPartyDto instructorPartyDto) {
        if(instructorPartyDto == null){
            throw new IllegalArgumentException("Instructor can not be null");
        }
        LearningParty learningParty = new LearningParty(instructorPartyDto.getEmail(),
                                        passwordEncoder.encode(instructorPartyDto.getPassWord()),
                                        new Authority(Role.ROLE_INSTRUCTOR));

        Instructor instructor = Instructor.builder()
                                                    .lastName(instructorPartyDto.getLastname())
                                                    .firstName(instructorPartyDto.getFirstname())
                                                    .learningParty(learningParty).build();
        return instructorRepository.save(instructor);
    }

}
