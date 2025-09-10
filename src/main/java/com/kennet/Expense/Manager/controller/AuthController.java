package com.kennet.Expense.Manager.controller;

import com.kennet.Expense.Manager.dto.LoginRequest;
import com.kennet.Expense.Manager.model.User;
import com.kennet.Expense.Manager.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request){
        try{
            User user = authService.authenticate(request.getEmail(), request.getPassword());
            return ResponseEntity.ok(Map.of(
                    "message", "Successful login!",
                    "email", user.getEmail(),
                    "rol", user.getRole()
                    ));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of(
                    "error", e.getMessage()
            ));
        }
    }
}
