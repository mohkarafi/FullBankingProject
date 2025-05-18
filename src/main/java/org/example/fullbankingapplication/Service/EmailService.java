package org.example.fullbankingapplication.Service;

import org.example.fullbankingapplication.BankAccountDTO.EmailDetails;

public interface EmailService {
    void sendEmail(EmailDetails emailDetails);

    void sendEmailWithAttachment(EmailDetails emailDetails);
}
