package org.example.fullbankingapplication.Service.Implt;

import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.example.fullbankingapplication.BankAccountDTO.EmailDetails;
import org.example.fullbankingapplication.Service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

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

    // methode pour envoyer pdf par mail
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
            FileSystemResource fileSystemResource = new FileSystemResource(emailDetails.getAttachment());
            mimeMessageHelper.addAttachment(Objects.requireNonNull(fileSystemResource.getFilename()), fileSystemResource);
            mailSender.send(mimeMessage);
            log.info("Email sent successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
/*
    @Override
    public void sendEmailWithAttachment(EmailDetails emailDetails) {
        MimeMessage mimeMessage = mailSender.createMimeMessage(); // instanciation de class MimeMessage de javaMail pour envoyer un mail ce forme d'un piece jointe
        MimeMessageHelper mimeMessageHelper; // declaration de Messsage Helper qui nous aide pour remplir ce mail
        try {
            mimeMessageHelper = new MimeMessageHelper(mimeMessage, true); // on remplit ce mail par le contenu de mim Message par une piece jointe
            mimeMessageHelper.setFrom(MailSender);
            mimeMessageHelper.setTo(emailDetails.getReciverEmail());
            mimeMessageHelper.setSubject(emailDetails.getEmailSubject());
            mimeMessageHelper.setText(emailDetails.getEmailBody());
            FileSystemResource file = new FileSystemResource(new File(emailDetails.getAttachment()));
            mimeMessageHelper.addAttachment(Objects.requireNonNull(file.getFilename()), file); // vérifie que le nom du fichier n’est pas null, sinon ça lève une exception.
            log.info(file.getFilename() + " has been sent to user with " + emailDetails.getReciverEmail());
            mailSender.send(mimeMessage);
            mailSender.send(mimeMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

}
