package com.poa.POAvanzados.Email;

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
