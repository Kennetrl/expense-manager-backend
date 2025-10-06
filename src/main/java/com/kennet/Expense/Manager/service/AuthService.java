package com.kennet.Expense.Manager.service;

import com.kennet.Expense.Manager.model.User;
import com.kennet.Expense.Manager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
/**
 * Authentication related operations.
 * <p>
 * Provides user authentication by validating email/password using
 * the configured {@link PasswordEncoder}.
 */
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Authenticates a user by email and raw password.
     * @param email user email
     * @param password raw password
     * @return authenticated {@link User}
     * @throws RuntimeException when user not found or password mismatch
     */
    public User authenticate(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        //First save password hashed
        if (!passwordEncoder.matches(password, user.getPasswordHash())) {
            throw new RuntimeException("Incorrect Password");
        }
        return user;
    }

}
