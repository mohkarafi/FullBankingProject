package org.example.fullbankingapplication.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity


public class SecurityConfig {
    private final UserDetailsService userDetailsService;
    private final JwtAuthentificationFilter jwtAuthentificationFilter;

    public SecurityConfig(UserDetailsService userDetailsService, JwtAuthentificationFilter jwtAuthentificationFilter) {
        this.userDetailsService = userDetailsService;
        this.jwtAuthentificationFilter = jwtAuthentificationFilter;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    SecurityFilterChain security(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) // désactive la protection contre les attaques CSRF (Cross Site Request Forgery), surtout utile pour les API REST sans session.
                .cors(AbstractHttpConfigurer::disable) // désactive la protection CORS
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers(HttpMethod.POST, "api/user").permitAll()
                            .requestMatchers(HttpMethod.POST, "api/user/login").permitAll()
                            .anyRequest().authenticated();
                });
        http.sessionManagement(session -> {
            session.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        });
        http.authenticationProvider(authenticationProvider());
        http.addFilterBefore(jwtAuthentificationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}