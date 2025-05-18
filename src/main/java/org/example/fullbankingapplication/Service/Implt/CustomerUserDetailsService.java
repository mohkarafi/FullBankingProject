package org.example.fullbankingapplication.Service.Implt;

import org.example.fullbankingapplication.Repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomerUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public CustomerUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) {
        System.out.println("Trying to load user with email: " + email);
        return userRepository.findUserByEmail(email).orElseThrow(() -> new UsernameNotFoundException(email + " Not found"));
    }
}
