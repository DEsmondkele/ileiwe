package com.ileiwe.data;

import com.ileiwe.data.model.Instructor;
import com.ileiwe.data.repository.InstructorRepository;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.ileiwe.data.model.Gender.MALE;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@SpringBootTest
@Slf4j
public class InstructorRepositoryTest {
    @Autowired
    InstructorRepository instructorRepositoryImpl;
    @BeforeEach
   public void setUp(){

        Instructor instructor = new Instructor();
    }

    @Test
    @Transactional
   public void  createInstructorTest(){
        Instructor instructor = new Instructor();
        instructor.setFirstName("Ben");
        instructor.setLastName("Carson");
        instructor.setId(1100L);
        instructor.setSpecialization("Java springboot");
        instructor.setBio("born into a family of business, study computer engineering at Lagos state university " +
                "firstclass student and overall best graduating student learn software engineering at Semicolon africa.");
        instructor.setGender(MALE);
        AssertionsForClassTypes.assertThat(instructor).isNotNull();
        AssertionsForClassTypes.assertThat(instructor.getId()).isNotNull();
        log.info("instructor before saving->{}",instructor);
        instructorRepositoryImpl.save(instructor);
        AssertionsForClassTypes.assertThat(instructor.getId()).isNotNull();
        log.info("instructor after saving->{}",instructor);

    }
    @Test
    @Transactional
    public void whenFindAllIsCalledThenSInstructorListIsReturned(){
        List<Instructor> instructors = instructorRepositoryImpl.findAll();
        assertThat(instructors).hasSize(1);
        log.info("product returned from database -->{}", instructors);
    }
}
