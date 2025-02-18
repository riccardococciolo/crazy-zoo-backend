package com.betacom.cz.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .anyRequest().permitAll() // 🔹 Rende tutte le API accessibili senza autenticazione
            )
            .csrf(csrf -> csrf.disable()) // 🔹 Disabilita la protezione CSRF per testare le API
            .formLogin(form -> form.disable()) // 🔹 Disabilita il login via form
            .httpBasic(httpBasic -> httpBasic.disable()); // 🔹 Disabilita l'autenticazione di base

        return http.build();
    }
}
