package org.example.fullbankingapplication.Service.Implt;

import org.example.fullbankingapplication.BankAccountDTO.TransactionRequest;
import org.example.fullbankingapplication.Entity.Transaction;
import org.example.fullbankingapplication.Repository.TransactionRepository;
import org.example.fullbankingapplication.Service.TransactionService;
import org.example.fullbankingapplication.enums.TransactionStatus;
import org.springframework.stereotype.Service;

@Service

public class TransactionServiceImplt implements TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionServiceImplt(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public void saveTransaction(TransactionRequest transactionDto) {
        Transaction transaction = Transaction.builder()
                .transactionType(transactionDto.getTransactionType())
                .transactionStatus(TransactionStatus.ACTIVATED)
                .AccountNumber(transactionDto.getAccountNumber())
                .amount(transactionDto.getAmount())
                .build();
        transactionRepository.save(transaction);
        System.out.println("transaction saved Successfully");
    }
}
