package org.example.fullbankingapplication.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionRequest {
    private String accountNumber;
    private String transactionType;
    private String transactionStatus;
    private BigDecimal amount;
}
