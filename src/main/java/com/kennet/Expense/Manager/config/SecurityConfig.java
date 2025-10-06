package com.kennet.Expense.Manager.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
/**
 * Security configuration for the API.
 * <p>
 * - Disables CSRF for stateless REST endpoints.
 * - Allows unauthenticated access to auth endpoints under /api/auth/**.
 * - Requires authentication for any other request.
 * - Exposes a BCrypt {@link PasswordEncoder} bean for password hashing.
 */
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.csrf(csrf -> csrf.disable()).authorizeHttpRequests(auth->auth
                .requestMatchers("/api/auth/**").permitAll().anyRequest().authenticated()
        );
        return http.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
