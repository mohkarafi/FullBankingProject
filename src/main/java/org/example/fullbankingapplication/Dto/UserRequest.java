package org.example.fullbankingapplication.Dto;

import lombok.*;

import java.math.BigDecimal;
@AllArgsConstructor @NoArgsConstructor @Builder @Getter @Setter
public class UserRequest {
    private String firstName ;
    private String lastName ;
    private String gender;
    private String email ;
    private String adresse ;
    private String StateOfOrigin;
    private String accountNumber;
    private BigDecimal balance ;
    private String PhoneNumber ;
}
