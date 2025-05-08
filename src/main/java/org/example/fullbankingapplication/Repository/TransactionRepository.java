package org.example.fullbankingapplication.Repository;

import org.example.fullbankingapplication.Entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long>
{
    // void CreateTransaction(Transaction transaction);
}
