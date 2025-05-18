package org.example.fullbankingapplication.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.fullbankingapplication.enums.TransactionStatus;
import org.example.fullbankingapplication.enums.TransactionType;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "Transactionn")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transactionIDDD")
    private Long transactionId;
    @Column(name = "TansactionType")
    private TransactionType transactionType;
    @Column(name = "TansactionStatus")
    private TransactionStatus transactionStatus;
    private String AccountNumber;
    private BigDecimal amount;
    @CreationTimestamp
    private LocalDate CreatedDate;
    @UpdateTimestamp
    private LocalDate updatedDate;
}
