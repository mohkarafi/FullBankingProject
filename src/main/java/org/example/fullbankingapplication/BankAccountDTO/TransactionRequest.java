package org.example.fullbankingapplication.BankAccountDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.fullbankingapplication.enums.TransactionStatus;
import org.example.fullbankingapplication.enums.TransactionType;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionRequest {
    private String accountNumber;
    private TransactionType transactionType;
    private TransactionStatus transactionStatus;
    private BigDecimal amount;
}
