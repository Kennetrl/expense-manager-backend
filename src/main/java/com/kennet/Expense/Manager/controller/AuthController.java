package com.kennet.Expense.Manager.controller;

import com.kennet.Expense.Manager.dto.LoginRequest;
import com.kennet.Expense.Manager.model.User;
import com.kennet.Expense.Manager.service.AuthService;
import com.kennet.Expense.Manager.service.JwtService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    private JwtService jwtService;

    public AuthController(AuthService authService, JwtService jwtService){
        this.authService = authService;
        this.jwtService = jwtService;
    }

    @PostMapping("login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request){
        try{
            User user = authService.authenticate(request.getEmail(), request.getPassword());

            Map<String, Object> extraClaims = new HashMap<>();
            extraClaims.put("role", user.getRole());
            extraClaims.put("name", user.getName());
            extraClaims.put("email", user.getEmail());

            String jwtToken = jwtService.generateToken(extraClaims, user);

            return ResponseEntity.ok(Map.of(
                    "message", "Successful login!",
                    "token", jwtToken,
                    "expiresIn", jwtService.getJwtExpirationMl(),
                    "data", Map.of(
                            "email", user.getEmail(),
                            "role", user.getRole().name()
                            )
                    ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of(
                    "Error", "Incorrect Credentials!"
            ));
        }
    }
}
