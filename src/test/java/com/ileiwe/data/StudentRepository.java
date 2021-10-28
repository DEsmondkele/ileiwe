package com.ileiwe.data;

import com.ileiwe.data.model.Gender;
import com.ileiwe.data.model.Student;
import com.ileiwe.data.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.Year;

@SpringBootTest
@Slf4j
class StudentRepositoryTest {

@Autowired
StudentRepository studentRepositoryImpl;

@BeforeEach
    void setUp(){

}

@Test
    public  void createStudentTest(){
    Student student = new Student();
    student.setFirstName("Ade");
    student.setLastName("Kunle");
    student.setGender(Gender.MALE);
    student.setDob(LocalDateTime.now());
    student.setDob(LocalDateTime.now().minusYears(20));
    student.setId(1001L);
    AssertionsForClassTypes.assertThat(student).isNotNull();
    AssertionsForClassTypes.assertThat(student.getId()).isNotNull();
    log.info("student before saving->{}",student);
    studentRepositoryImpl.save(student);
    AssertionsForClassTypes.assertThat(student.getId()).isNotNull();
    log.info("student after saving->{}",student);



}



}
