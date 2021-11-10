package com.ileiwe.service.event;

import com.ileiwe.service.mail.EmailService;
import com.ileiwe.service.mail.Message;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import java.util.UUID;

@Component
public class ApplicationEventListener {
    @Autowired
    EmailService emailService;
    @Autowired
    TemplateEngine templateEngine;
    @EventListener
    public  void handleRegistrationCompleteEvent(OnRegistrationCompleteEvent event) throws UnirestException {
        this.confirmRegistration(event);
    }
    private void confirmRegistration(OnRegistrationCompleteEvent event) throws UnirestException {
    //String verificationToken= UUID.randomUUID().toString();
        Message message = Message.builder()
                .from("auduigbekele@gmail.com")
                .to(event.getAppUser().getEmail())
                .subject("Confirm email")
                .body(templateEngine.process("confirmation.html",new Context())).build();
        emailService.send(message);
    }

}
