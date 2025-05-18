package org.example.fullbankingapplication.Service;

import org.example.fullbankingapplication.BankAccountDTO.*;
import org.example.fullbankingapplication.Entity.User;
import org.springframework.data.domain.Page;

public interface UserService {
    BankResponse createAccount(UserRequest userRequest);

    BankResponse BalanceEnquiry(CheckBalance checkBalance);

    String getNameInfo(CheckBalance checkBalance);

    BankResponse CreditAccount(CreditDebitRequest creditDebitrequest);

    BankResponse DebitAccount(CreditDebitRequest creditDebitrequest);

    BankResponse TransferAmount(TransferRequest transferRequest);

    Page<User> findAllAccounts(int page, int size);

    BankResponse login(LoginDto loginDto);

}
