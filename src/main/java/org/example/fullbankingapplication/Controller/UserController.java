package org.example.fullbankingapplication.Controller;

import org.example.fullbankingapplication.Dto.BankResponse;
import org.example.fullbankingapplication.Dto.UserRequest;
import org.example.fullbankingapplication.Entity.User;
import org.example.fullbankingapplication.Service.Implt.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public BankResponse CreateUser(@RequestBody UserRequest user){
        return userService.createAccount( user);
    }

}
