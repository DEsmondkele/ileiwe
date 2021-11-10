package com.ileiwe.service.mail;

import com.mashape.unirest.http.exceptions.UnirestException;
import org.apache.coyote.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MailGunEmailServiceImplTest {
    @Autowired
    @Qualifier("mailgun")
    EmailService emailService;



    @BeforeEach
    void setUp() {
    }

    @Test
        void sendEmailWithMailGun() throws UnirestException {
        Message mail = Message.builder()
                .from("auduigbekele@gmail").to("enoch5353@gmail.com")
                .subject("Test email")
                .body("This is my body")
                .build();
      MailResponse response = emailService.send(mail);
        assertTrue(response.isSuccessful());
        }



    @AfterEach
    void tearDown() {
    }
}