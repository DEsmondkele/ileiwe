package com.ileiwe.data.repository;

import com.ileiwe.data.model.Authority;
import com.ileiwe.data.model.LearningParty;
import com.ileiwe.data.model.Role;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolationException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
@Sql(scripts = {"/db/insert.sql"})
@Rollback(value = false)
class LearningPartyRepositoryTest {
    @Autowired
    LearningPartyRepository learningPartyRepository;

    @BeforeEach
    void setUp() {
    }
    @Test
    @Transactional
    void createLearningPartyWithStudentROleTest(){

        LearningParty learningUser =
                new LearningParty("enoch5353@gmail.com" ,
                        "enoch5353",new Authority(Role.ROLE_STUDENT));
        learningPartyRepository.save(learningUser);
    assertThat(learningUser.getId()).isNotNull();
    assertThat(learningUser.getEmail()).isNotNull().isEqualTo("enoch5353@gmail.com");
        //assertThat(learningUser.getAuthorities().get(0).getAuthority()).isEqualTo(learningUser.getAuthorities());
        log.info("After saving -->{}",learningUser);
        assertThat(learningUser.getAuthorities().get(0)).isNotNull().isEqualTo(learningUser.getAuthorities());
    }
    @Test

    void createLearningPartyHaveUniqueEmailsTest(){
        LearningParty User1 =
                new LearningParty("enoch5353@gmail.com" ,
                        "enoch5353",new Authority(Role.ROLE_STUDENT));
        learningPartyRepository.save(User1);
        assertThat(User1.getEmail()).isEqualTo("enoch5353@gmail.com");
        assertThat(User1.getId()).isNotNull();

        LearningParty User2 =
                new LearningParty("enoch5353@gmail.com" ,
                        "enoch5353",new Authority(Role.ROLE_STUDENT));
        assertThrows(DataIntegrityViolationException.class,()
                ->learningPartyRepository.save(User2));

    }
    @Test
    void learningPartyWithNullAndEmptyValuesTest(){

        LearningParty User1 =
                new LearningParty(null ,
                        null, new Authority(Role.ROLE_STUDENT));
        assertThrows(ConstraintViolationException.class,()
                ->learningPartyRepository.save(User1));

    }
@Test
void learningPartyWithEmptyStringTest(){
    LearningParty User2 =
            new LearningParty(" " ,
                    " ", new Authority(Role.ROLE_STUDENT));
    assertThrows(ConstraintViolationException.class,()
            ->learningPartyRepository.save(User2));
}
@Test
@Transactional
   void findUserByEmailTest(){
LearningParty learningParty = learningPartyRepository.findByEmail("tomi@gmail.com");
assertThat(learningParty).isNotNull();
assertThat(learningParty.getEmail()).isEqualTo("tomi@gmail.com");
log.info("Learning party object -->{}", learningParty);
}

    @AfterEach
    void tearDown() {
    }
}