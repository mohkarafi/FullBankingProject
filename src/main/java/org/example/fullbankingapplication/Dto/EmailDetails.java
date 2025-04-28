package org.example.fullbankingapplication.Dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmailDetails {
    private String RecpentEmail;
    private String EmailSubject;
    private String EmailBody;
}
