package org.example.fullbankingapplication.Service.Implt;

import jakarta.validation.constraints.Email;
import org.example.fullbankingapplication.Dto.EmailDetails;

public interface EmailService {
    void SendEmail(EmailDetails emailDetails);
}
