package com.poa.POAvanzados.Controller;

import com.poa.POAvanzados.Model.Email.EmailDetails;
import com.poa.POAvanzados.Service.EmailService;
import com.poa.POAvanzados.Service.EmailServiceImpl;
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
