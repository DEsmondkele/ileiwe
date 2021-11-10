package com.ileiwe.service;

import com.ileiwe.data.dto.CourseDto;
import com.ileiwe.data.dto.InstructorPartyDto;
import com.ileiwe.data.model.*;
import com.ileiwe.data.repository.CourseRepository;
import com.ileiwe.data.repository.InstructorRepository;
import com.ileiwe.data.repository.LearningPartyRepository;
import com.ileiwe.service.event.OnRegistrationCompleteEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InstructorServiceImpl implements InstructorService{
    @Autowired
    ApplicationEventPublisher eventPublisher;

    @Autowired
    InstructorRepository instructorRepository;
    @Autowired
    LearningPartyRepository learningPartyRepository;
    @Autowired
    CourseRepository courseRepository;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;


    @Override
    public Instructor save(InstructorPartyDto instructorPartyDto) throws UserAlreadyExistException {
        if (instructorPartyDto == null) {
            throw new IllegalArgumentException("Instructor can not be null");
        }

        if (learningPartyRepository.findByEmail(instructorPartyDto.getEmail()) == null) {
            LearningParty learningParty = new LearningParty(instructorPartyDto.getEmail(),
                    passwordEncoder.encode(instructorPartyDto.getPassWord()),
                    new Authority(Role.ROLE_INSTRUCTOR));

            Instructor instructor = Instructor.builder()
                    .lastName(instructorPartyDto.getLastname())
                    .firstName(instructorPartyDto.getFirstname())
                    .learningParty(learningParty).build();
            eventPublisher.publishEvent
                    (new OnRegistrationCompleteEvent(learningParty));

            return instructorRepository.save(instructor);
        }
        else{
            throw  new UserAlreadyExistException("user with email" + instructorPartyDto.getEmail()+"already exist");
        }
    }

    @Override
    public Optional<Instructor> findById(InstructorPartyDto instructorPartyDto){
       if(instructorPartyDto == null) {
           throw new IllegalArgumentException("No null instructor");}
       return instructorRepository.findById(instructorPartyDto);
    }


    @Override
    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }

    @Override
    public Optional<Course> findById(Long id,CourseDto courseDto) {
        return courseRepository.findById(id);
    }


}
