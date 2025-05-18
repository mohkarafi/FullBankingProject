package org.example.fullbankingapplication.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.fullbankingapplication.BankAccountDTO.*;
import org.example.fullbankingapplication.Entity.User;
import org.example.fullbankingapplication.Repository.UserRepository;
import org.example.fullbankingapplication.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user")
@Tag(name = "User Managment Api")
public class UserController {
    @Autowired
    private UserService userService;
    private UserRepository userRepository;

    @Operation(
            summary = "Create new user Account",
            description = "creating a new user ans assigning an account Id"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Http status 201 created"
    )

    @PostMapping
    public BankResponse CreateUser(@RequestBody UserRequest user) {
        return userService.createAccount(user);
    }

    @Operation(
            summary = "Checck Balance",
            description = "Given an Account Number and Checking the balance SUCCES"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http status 200 "
    )

    @GetMapping("checkBalance")
    public BankResponse Checkbalance(@RequestBody CheckBalance checkBalance) {
        return userService.BalanceEnquiry(checkBalance);
    }

    @GetMapping("getName")
    public String getNameInfo(@RequestBody CheckBalance checkBalance) {
        return userService.getNameInfo(checkBalance);
    }


    @Operation(
            summary = "Credit an Account"

    )
    @ApiResponse(
            responseCode = "200",
            description = "Http status 200 "
    )

    @GetMapping("credit")
    public BankResponse CreditBalance(@RequestBody CreditDebitRequest creditDebitrequest) {
        return userService.CreditAccount(creditDebitrequest);
    }

    @Operation(
            summary = "Dredit an Account"

    )
    @ApiResponse(
            responseCode = "200",
            description = "Http status 200 "
    )
    @GetMapping("debit")
    public BankResponse DebitBalance(@RequestBody CreditDebitRequest creditDebitrequest) {
        return userService.DebitAccount(creditDebitrequest);
    }

    @PostMapping("transfer")
    public BankResponse TransferOperation(@RequestBody TransferRequest transferRequest) {
        return userService.TransferAmount(transferRequest);
    }

    @PostMapping("login")
    public BankResponse login(@RequestBody LoginDto login) {
        return userService.login(login);
    }

    @GetMapping("Accounts")
    public Page<User> findAllAccounts(@RequestParam(defaultValue = "O") int page,
                                      @RequestParam(defaultValue = "4") int size) {
        return userService.findAllAccounts(page, size);
    }

}
