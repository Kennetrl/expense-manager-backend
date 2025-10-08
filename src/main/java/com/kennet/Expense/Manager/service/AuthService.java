package com.kennet.Expense.Manager.service;

import com.kennet.Expense.Manager.dto.RegisterRequest;
import com.kennet.Expense.Manager.model.Role;
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

    /**
     * Registers a new user in the system.
     */
    public User register(RegisterRequest request) {
        // Verificar si el usuario ya existe
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email is already registered");
        }

        // Crear nuevo usuario
        User user = new User();
        user.setName(request.getName());
        user.setLastname(request.getLastname());
        user.setEmail(request.getEmail());
        user.setPasswordHash(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.USER); // o "ADMIN" si quieres asignar manualmente

        // Guardar en base de datos
        return userRepository.save(user);
    }
}
