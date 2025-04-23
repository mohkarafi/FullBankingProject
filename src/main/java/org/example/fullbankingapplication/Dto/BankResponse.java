package org.example.fullbankingapplication.Dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter @Builder
public class BankResponse {
    private String ResponseMessage ;
    private String ResponseCode ;
    private AccountInfo accountinfo ;
}
