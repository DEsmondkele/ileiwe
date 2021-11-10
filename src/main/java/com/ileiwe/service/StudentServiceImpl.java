package com.ileiwe.service;

import com.ileiwe.data.dto.StudentPartyDto;
import com.ileiwe.data.model.*;
import com.ileiwe.data.repository.CourseRepository;
import com.ileiwe.data.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService{

    @Autowired
    StudentRepository studentRepository;
    @Autowired
    CourseRepository courseRepo;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Override
    public Student save(StudentPartyDto studentPartyDto) {
        if(studentPartyDto == null){
            throw new IllegalArgumentException("Student can not be null");
        }
        LearningParty learningParty = new LearningParty(studentPartyDto.getEmail(),
                passwordEncoder.encode(studentPartyDto.getPassWord()),
                new Authority(Role.ROLE_STUDENT));
        Student student = Student.builder()
                .lastName(studentPartyDto.getLastname())
                .firstName(studentPartyDto.getFirstname())
                .learningParty(learningParty).build();
        return studentRepository.save(student);
    }

    @Override
    public Optional<Course> viewCourse(Long id) {
       return courseRepo.findById(id);

    }


//    @Override
//    public LearningParty login(StudentPartyDto studentPartyDto) throws LoginException {
//        verifyStudent(studentPartyDto);
//        LearningParty learningParty = new LearningParty(studentRepository.getClass());
//
//        return learningParty;
//    }
//    public void verifyStudent(StudentPartyDto studentPartyDto) throws LoginException {
//        Student student = new Student();
//        String userEmail = studentPartyDto.getEmail();
//        String userPassword = studentPartyDto.getPassWord();
//        String email = student.getEmail();
//        String password = student.getPassword();
//        if (!Objects.equals(userEmail, email) && !Objects.equals(userPassword, password))
//            throw new LoginException("Incorrect email or password");
//    }
}
