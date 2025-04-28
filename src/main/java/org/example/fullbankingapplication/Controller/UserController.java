package org.example.fullbankingapplication.Controller;

import org.example.fullbankingapplication.Dto.CheckBalance;
import org.example.fullbankingapplication.Dto.BankResponse;
import org.example.fullbankingapplication.Dto.CreditDebitrequest;
import org.example.fullbankingapplication.Dto.UserRequest;
import org.example.fullbankingapplication.Entity.User;
import org.example.fullbankingapplication.Repository.UserRepository;
import org.example.fullbankingapplication.Service.Implt.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/user")
public class UserController {
    @Autowired
    private UserService userService;
    private UserRepository userRepository;
    @PostMapping
    public BankResponse CreateUser(@RequestBody UserRequest user){
        return userService.createAccount( user);
    }

    @GetMapping("checkBalance")
    public BankResponse Checkbalance(@RequestBody CheckBalance checkBalance){
        return userService.BalanceEnquiry( checkBalance);
    }

    @GetMapping("getName")
    public String getNameInfo(@RequestBody CheckBalance checkBalance){
        return userService.getNameInfo(checkBalance);
    }
    @GetMapping("credit")
    public BankResponse CreditBalance(@RequestBody CreditDebitrequest creditDebitrequest){
        return userService.CreditAccount(creditDebitrequest);
    }
    @GetMapping("debit")
    public BankResponse DebitBalance(@RequestBody  CreditDebitrequest creditDebitrequest){
        return userService.DebitAccount(creditDebitrequest);
    }

}
