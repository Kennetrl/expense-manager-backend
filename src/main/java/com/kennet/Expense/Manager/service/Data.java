package com.kennet.Expense.Manager.service;

import com.kennet.Expense.Manager.model.Role;
import com.kennet.Expense.Manager.model.User;
import com.kennet.Expense.Manager.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class Data {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void init(){
        User admin = new User();
        admin.setEmail("joel@hotmail.com");
        admin.setPasswordHash(passwordEncoder.encode("Admin123"));
        admin.setRole(Role.ADMIN);
        admin.setName("Joel");
        userRepository.save(admin);
    }

}
