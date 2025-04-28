package org.example.fullbankingapplication.Service.Implt;

import org.example.fullbankingapplication.Dto.CheckBalance;
import org.example.fullbankingapplication.Dto.BankResponse;
import org.example.fullbankingapplication.Dto.CreditDebitrequest;
import org.example.fullbankingapplication.Dto.UserRequest;

public interface UserService {
    BankResponse createAccount(UserRequest userRequest);
    BankResponse BalanceEnquiry(CheckBalance checkBalance);
    String getNameInfo(CheckBalance checkBalance);
    BankResponse CreditAccount(CreditDebitrequest creditDebitrequest);
    BankResponse DebitAccount(CreditDebitrequest creditDebitrequest);


}
