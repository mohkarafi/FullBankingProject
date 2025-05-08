package org.example.fullbankingapplication.Dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AccountInfo {
    @Schema(
            name = "User Account Balance"

    )
    private BigDecimal AccountBalance ;


    @Schema(
            name = "User Account Name"

    )
    private String AccountName ;


    @Schema(
            name = "User Account Number"

    )
    private String AccountNumber ;
}
