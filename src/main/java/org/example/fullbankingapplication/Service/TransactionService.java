package org.example.fullbankingapplication.Service;

import org.example.fullbankingapplication.BankAccountDTO.TransactionRequest;
import org.springframework.stereotype.Service;

@Service
public interface TransactionService {
    void saveTransaction(TransactionRequest transactionDto);
}
