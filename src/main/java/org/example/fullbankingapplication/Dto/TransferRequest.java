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

public class TransferRequest {
    private String SenderAccountNumber;
    private String DestinaterAccountNumber;
    private BigDecimal amount;
}
