package org.example.fullbankingapplication.Service.Implt;

import org.example.fullbankingapplication.Dto.TransactionRequest;
import org.springframework.stereotype.Service;

@Service
public interface TransactionService {
    void saveTransaction(TransactionRequest transactionDto);
}
