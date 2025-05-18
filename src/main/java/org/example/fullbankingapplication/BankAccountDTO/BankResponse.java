package org.example.fullbankingapplication.BankAccountDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class BankResponse {
    @Schema(
            description = "The response code for the response ",
            name = "firstName",
            type = "string",
            example = "Vatsal")
    private String ResponseCode;
    private String ResponseMessage;
    private AccountInfo accountinfo;
}
