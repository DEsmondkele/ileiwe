package com.ileiwe.service.mail;

import com.mashape.unirest.http.exceptions.UnirestException;

public interface EmailService {
    MailResponse send(Message message) throws UnirestException;
}
