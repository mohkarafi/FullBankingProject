package org.example.fullbankingapplication.Controller;

import com.itextpdf.text.DocumentException;
import org.example.fullbankingapplication.Entity.Transaction;
import org.example.fullbankingapplication.Service.Implt.BankStatement;
import org.example.fullbankingapplication.Service.TransactionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.util.List;

@RestController

public class TransactionController {
    private final TransactionService transactionService;
    private final BankStatement bankStatement;

    public TransactionController(TransactionService transactionService, BankStatement accountStatement) {
        this.transactionService = transactionService;
        this.bankStatement = accountStatement;
    }

    @GetMapping("Transactions")
    public List<Transaction> getAllTransactions(
            @RequestParam String AccountNumber,
            @RequestParam String StartDate,
            @RequestParam String EndDate
    ) throws DocumentException, FileNotFoundException {
        return bankStatement.getAllTransactions(AccountNumber, StartDate, EndDate);
    }
}
