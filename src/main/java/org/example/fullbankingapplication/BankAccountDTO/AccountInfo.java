package org.example.fullbankingapplication.BankAccountDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountInfo {
    private String AccountName;
    private BigDecimal AccountBalance;
    private String AccountNumber;
}
