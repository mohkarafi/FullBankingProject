package org.example.fullbankingapplication.BankAccountDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class EmailDetails {
    private String ReciverEmail;
    private String EmailBody;
    private String EmailSubject;
    private String attachment;
}
