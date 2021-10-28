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
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j

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
        assertThat(learningUser.getAuthorities().get(0).getAuthority()).isEqualTo(Role.ROLE_STUDENT);
        log.info("After saving -->{}",learningUser);
    }


    @AfterEach
    void tearDown() {
    }
}