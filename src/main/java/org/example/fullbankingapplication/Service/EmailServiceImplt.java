package org.example.fullbankingapplication.Service;

import org.example.fullbankingapplication.Dto.EmailDetails;
import org.example.fullbankingapplication.Service.Implt.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImplt implements EmailService {
    @Autowired
   private  JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String MailSender;


    @Override
    public void SendEmail(EmailDetails emailDetails) {
      try{
          SimpleMailMessage message = new SimpleMailMessage();
          message.setFrom(MailSender);
          message.setTo(emailDetails.getRecpentEmail());
          message.setSubject(emailDetails.getEmailSubject());
          message.setText(emailDetails.getEmailBody());
          javaMailSender.send(message);
          System.out.println("Email sent successfully");
      }catch (Exception e){
          e.printStackTrace();
      }

    }
}
