package org.example.fullbankingapplication.Service;

import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.example.fullbankingapplication.Dto.EmailDetails;
import org.example.fullbankingapplication.Service.Implt.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Objects;

@Service
@Slf4j
public class EmailServiceImplt implements EmailService {

    @Autowired
    private JavaMailSender mailSender;
    @Value("${spring.mail.username}")
    private String MailSender;


    @Override
    public void sendEmail(EmailDetails emailDetails) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(MailSender);
            message.setTo(emailDetails.getReciverEmail());
            message.setSubject(emailDetails.getEmailSubject());
            message.setText(emailDetails.getEmailBody());
            mailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendEmailWithAttachment(EmailDetails emailDetails) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;
        try {
            mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(MailSender);
            mimeMessageHelper.setTo(emailDetails.getReciverEmail());
            mimeMessageHelper.setSubject(emailDetails.getEmailSubject());
            mimeMessageHelper.setText(emailDetails.getEmailBody());
            FileSystemResource file = new FileSystemResource(new File(emailDetails.getAttachment()));
            mimeMessageHelper.addAttachment(Objects.requireNonNull(file.getFilename()), file);
            mailSender.send(mimeMessage);
            log.info(file.getFilename() + "has been sent to user with " + emailDetails.getReciverEmail());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
