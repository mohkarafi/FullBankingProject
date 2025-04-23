package org.example.fullbankingapplication.Service.Implt;

import org.example.fullbankingapplication.Dto.AccountInfo;
import org.example.fullbankingapplication.Dto.BankResponse;
import org.example.fullbankingapplication.Dto.UserRequest;

public interface UserService {
    BankResponse createAccount(UserRequest userRequest);

}
