package org.example.fullbankingapplication.Service;

import org.example.fullbankingapplication.Dto.AccountInfo;
import org.example.fullbankingapplication.Dto.BankResponse;
import org.example.fullbankingapplication.Dto.UserRequest;
import org.example.fullbankingapplication.Entity.User;
import org.example.fullbankingapplication.Repository.UserRepository;
import org.example.fullbankingapplication.Service.Implt.UserService;
import org.example.fullbankingapplication.Utilis.AccountUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
@Service
public class UserImplementation implements  UserService {

    private UserRepository userRepository;
    @Autowired
    public UserImplementation(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public BankResponse createAccount(UserRequest userRequest) {
        if(userRepository.existsByEmail(userRequest.getEmail())){
            BankResponse bankresponse = BankResponse.builder()
                    .ResponseCode(AccountUtil.ACCOUNT_EXISTS_CODE)
                    .ResponseMessage(AccountUtil.ACCOUNT_EXISTS_MESSAGE)
                    .accountinfo(null)
                    .build();
        }
        User user = User.builder()
                .firstName(userRequest.getFirstName())
                .lastName(userRequest.getLastName())
                .gender(userRequest.getGender())
                .email(userRequest.getEmail())
                .adresse(userRequest.getAdresse())
                .StateOfOrigin(userRequest.getStateOfOrigin())
                .accountNumber(AccountUtil.generateAccountNumber())
                .balance(BigDecimal.ZERO)
                .status("Active")
                .PhoneNumber(String.valueOf(userRequest.getPhoneNumber()))
                .build();
        User SavedUser = userRepository.save(user);
        BankResponse savedResponse = BankResponse.builder()
                .ResponseCode(AccountUtil.ACCOUNT_CREATION_CODE)
                .ResponseMessage(AccountUtil.ACCOUNT_CREATION_MESSAGE)
                .accountinfo(AccountInfo.builder()
                        .AccountName(SavedUser.getFirstName() + " " + SavedUser.getLastName())
                        .AccountNumber(SavedUser.getAccountNumber())
                        .AccountBalance(SavedUser.getBalance())
                        .build())
                .build();
              return savedResponse;
    }
}





