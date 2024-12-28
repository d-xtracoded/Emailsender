package com.prime.EmailSender.service;

import com.prime.EmailSender.dto.EmailRequest;
import com.prime.EmailSender.model.Email;
import com.prime.EmailSender.repo.EmailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private EmailRepository emailRepository;

    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    private final JavaMailSender javaMailSender;

    public String sendEmail(EmailRequest emailRequest){
       try {
           SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
           simpleMailMessage.setTo(emailRequest.getTo());
           simpleMailMessage.setSubject(emailRequest.getSubject());
           simpleMailMessage.setText(emailRequest.getBody());
           javaMailSender.send(simpleMailMessage);
                   //.send(simpleMailMessage);

           // Save to DB
           Email emailtosave = new Email();
           emailtosave.setBody(emailRequest.getBody());
           emailtosave.setRecipient(emailRequest.getTo());
           emailtosave.setSubject(emailRequest.getSubject());
           emailRepository.save(emailtosave);
           return  "Email successfully sent";
       } catch (Exception e){
           return e.getMessage();
       }

    }
}
