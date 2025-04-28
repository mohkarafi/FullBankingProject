package org.example.fullbankingapplication.Repository;

import org.example.fullbankingapplication.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Boolean existsByEmail(String email);
    Boolean existsByAccountNumber(String email);
    User findByAccountNumber(String accountNumber);
    User findByFirstName(String firstName);
}
