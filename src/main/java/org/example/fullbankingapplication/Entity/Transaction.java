package org.example.fullbankingapplication.Entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table (name = "Transactionn")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transactionIDDD")
    private Long transactionId;
    private String transactionType;
    private String transactionStatus;
    private String  AccountNumber;
    private BigDecimal amount;
    @CreationTimestamp
    private LocalDate CreatedDate;
    @UpdateTimestamp
    private LocalDate updatedDate;
}
