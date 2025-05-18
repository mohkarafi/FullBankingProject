package org.example.fullbankingapplication.Repository;

import org.example.fullbankingapplication.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Boolean existsByEmail(String email);

    Boolean existsByAccountNumber(String email);

    User findByAccountNumber(String accountNumber);

    User findByFirstName(String firstName);

    Optional<UserDetails> findUserByEmail(String email);
}
