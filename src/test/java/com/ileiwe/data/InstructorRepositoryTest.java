package com.ileiwe.data;

import com.ileiwe.data.model.Authority;
import com.ileiwe.data.model.Instructor;
import com.ileiwe.data.model.LearningParty;
import com.ileiwe.data.model.Role;
import com.ileiwe.data.repository.InstructorRepository;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolationException;
import java.util.List;

import static com.ileiwe.data.model.Gender.MALE;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Slf4j
@Sql(scripts = ("/db/insert.sql"))
public class InstructorRepositoryTest {
    @Autowired
    InstructorRepository instructorRepository;
    @BeforeEach
   public void setUp(){

        Instructor instructor = new Instructor();
    }

    @Test
    @Transactional
    @Rollback(value = false)
   public void  createInstructorTest(){
        Instructor instructor = new Instructor();
        instructor.setFirstName("Ben");
        instructor.setLastName("Carson");
        instructor.setId(instructor.getId());
        instructor.setSpecialization("Java springboot");
        instructor.setBio("born into a family of business, study computer engineering at Lagos state university " +
                "firstclass student and overall best graduating student learn software engineering at Semicolon africa.");
        instructor.setGender(MALE);
        AssertionsForClassTypes.assertThat(instructor).isNotNull();
        AssertionsForClassTypes.assertThat(instructor.getId()).isNotNull();
        log.info("instructor before saving->{}",instructor);
        instructorRepository.save(instructor);
        AssertionsForClassTypes.assertThat(instructor.getId()).isNotNull();
        log.info("instructor after saving->{}",instructor);

    }
    @Test
    void saveInstructorAsLearningPartyTest(){
        LearningParty  user = new LearningParty("trainer@email.com","1234pass",new Authority(Role.ROLE_INSTRUCTOR));
        Instructor instructor = Instructor.builder().firstName("Mike").lastName("Alao").learningParty(user).build();
        instructorRepository.save(instructor);
        assertThat(instructor.getId()).isNotNull();
        assertThat(instructor.getLearningParty().getId()).isNotNull();
        log.info("Instructor after saving -->{}",instructor);
    }


    @Test
    @Transactional
    public void whenFindAllIsCalledThenSInstructorListIsReturned(){
        List<Instructor> instructors = instructorRepository.findAll();
        assertThat(instructors).hasSize(1);
        log.info("product returned from database -->{}", instructors);
    }

    @Test
    @Transactional
    @Rollback(value = false)
    void updateInstructorTableByIdTest(){
        LearningParty  user = new LearningParty("trainer@email.com","1234pass",new Authority(Role.ROLE_INSTRUCTOR));
        Instructor instructor = Instructor.builder().firstName("Mike").lastName("Alao").learningParty(user).build();
        instructorRepository.save(instructor);
        assertThat(instructor.getId()).isNotNull();
        assertThat(instructor.getLearningParty().getId()).isNotNull();
        log.info("Instructor after saving -->{}",instructor);

        Instructor savedInstructor =instructorRepository.findById(instructor.getId()).orElse(null);
        log.info("saved Instructor after saving -->{}",savedInstructor);
                assertThat(savedInstructor).isNotNull();
                assertThat(savedInstructor.getBio()).isNull();
                assertThat(savedInstructor.getGender()).isNull();

                savedInstructor.setBio("I Am Java Instructor");
                savedInstructor.setGender(MALE);
        assertThat(savedInstructor.getBio()).isNotNull();
        assertThat(savedInstructor.getGender()).isNotNull();


    }
    @Test
    void createInstructorWithNullValuesTest(){
        LearningParty  user = new LearningParty("trainer@email.com","1234pass",new Authority(Role.ROLE_INSTRUCTOR));
        Instructor instructor = Instructor.builder().firstName(null).lastName(null).learningParty(user).build();
        assertThrows(ConstraintViolationException.class,()->instructorRepository.save(instructor));
    }
    @Test
    void createInstructorWithEmptyStringValuesTest(){
        LearningParty  user = new LearningParty("trainer@email.com","1234pass",new Authority(Role.ROLE_INSTRUCTOR));
        Instructor instructor = Instructor.builder().firstName(" ").lastName(" ").learningParty(user).build();
        assertThrows(ConstraintViolationException.class,()->instructorRepository.save(instructor));
    }


}
