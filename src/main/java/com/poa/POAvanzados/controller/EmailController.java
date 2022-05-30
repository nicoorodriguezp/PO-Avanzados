package com.poa.POAvanzados.controller;

import com.poa.POAvanzados.model.email.EmailDetails;
import com.poa.POAvanzados.service.EmailService;
import com.poa.POAvanzados.service.EmailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

public class EmailController {

    @Autowired
    private EmailService emailService = new EmailServiceImpl();

    public String sendMail(EmailDetails details) {
        String status = emailService.sendSimpleMail(details);
        System.out.println(status);
        return status;
    }

    public String sendMailWithAttachment(EmailDetails details) {
        String status = emailService.sendMailWithAttachment(details);
        return status;
    }
}
