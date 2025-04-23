package org.example.fullbankingapplication.Dto;

import lombok.*;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AccountInfo {
    private BigDecimal AccountBalance ;
    private String AccountName ;
    private String AccountNumber ;
}
