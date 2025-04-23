package org.example.fullbankingapplication.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@AllArgsConstructor @NoArgsConstructor
@Builder
@Getter
@Setter

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private String firstName ;
    private String lastName ;
    private String gender;
    private String email ;
    private String adresse ;
    private String StateOfOrigin;
    private String accountNumber;
    private BigDecimal balance ;
    private String status ;
    private String PhoneNumber ;
    @CreationTimestamp
    private LocalDate createdate ;
    @UpdateTimestamp
    private LocalDate updatedate ;

}
