package org.example.fullbankingapplication.Dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter @Builder
public class BankResponse {
    private String ResponseCode ;
    private String ResponseMessage ;
    private AccountInfo accountinfo ;
}
