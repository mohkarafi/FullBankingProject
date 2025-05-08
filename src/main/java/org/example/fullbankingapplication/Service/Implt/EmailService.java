package org.example.fullbankingapplication.Service.Implt;

import org.example.fullbankingapplication.Dto.EmailDetails;

public interface EmailService {
    void sendEmail(EmailDetails emailDetails);

    void sendEmailWithAttachment(EmailDetails emailDetails);
}
