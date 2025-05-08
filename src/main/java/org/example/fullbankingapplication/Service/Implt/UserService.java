package org.example.fullbankingapplication.Service.Implt;

import org.example.fullbankingapplication.Dto.*;
import org.example.fullbankingapplication.Entity.User;

import java.util.List;

public interface UserService {
    BankResponse createAccount(UserRequest userRequest);
    BankResponse BalanceEnquiry(CheckBalance checkBalance);
    String getNameInfo(CheckBalance checkBalance);
    BankResponse CreditAccount(CreditDebitRequest creditDebitrequest);
    BankResponse DebitAccount(CreditDebitRequest creditDebitrequest);
   BankResponse TransferAmount(TransferRequest transferRequest);
   List<User> findAllAccounts();

}
