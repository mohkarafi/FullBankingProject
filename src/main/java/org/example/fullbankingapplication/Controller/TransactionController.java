package org.example.fullbankingapplication.Controller;

import com.itextpdf.text.DocumentException;
import org.example.fullbankingapplication.Entity.Transaction;
import org.example.fullbankingapplication.Service.BankStatement;
import org.example.fullbankingapplication.Service.Implt.TransactionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.util.List;

@RestController

public class TransactionController {
    private final TransactionService transactionService;
    private final BankStatement accountStatement;

    public TransactionController(TransactionService transactionService, BankStatement accountStatement) {
        this.transactionService = transactionService;
        this.accountStatement = accountStatement;
    }

    @GetMapping("Transactions")
    public List<Transaction> getAllTransactions(
            @RequestParam String AccountNumber,
            @RequestParam String StartDate,
            @RequestParam String EndDate
    ) throws DocumentException, FileNotFoundException {
        return accountStatement.getAllTransactions(AccountNumber, StartDate, EndDate);
    }
}
