package org.example.fullbankingapplication.Dto;

import lombok.*;

import java.math.BigDecimal;
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class AccountResponse {
    private String firstName ;
    private String lastName ;
    private String email ;
    private String accountNumber;
    private BigDecimal balance ;
}
