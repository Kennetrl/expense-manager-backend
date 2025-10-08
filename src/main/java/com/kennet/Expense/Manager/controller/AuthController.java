package com.kennet.Expense.Manager.controller;

import com.kennet.Expense.Manager.dto.LoginRequest;
import com.kennet.Expense.Manager.dto.RegisterRequest;
import com.kennet.Expense.Manager.model.User;
import com.kennet.Expense.Manager.service.AuthService;
import com.kennet.Expense.Manager.service.JwtService;
import com.kennet.Expense.Manager.utils.AuthResponseBuilder;
import com.kennet.Expense.Manager.utils.ErrorResponseBuilder;
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
/**
 * Authentication endpoints.
 * <p>
 * Exposes login to authenticate a user and return a signed JWT plus safe user data.
 */
public class AuthController {

    @Autowired
    private AuthService authService;

    private JwtService jwtService;

    public AuthController(AuthService authService, JwtService jwtService){
        this.authService = authService;
        this.jwtService = jwtService;
    }

    @PostMapping("login")
    /**
     * Authenticates a user using email/password and returns a JWT with extra claims.
     * @param request login payload with email and password (validated)
     * @return 200 with token and user info; 401 with error payload on failure
     */
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request){
        try{
            User user = authService.authenticate(request.getEmail(), request.getPassword());

            Map<String, Object> extraClaims = new HashMap<>();
            extraClaims.put("role", user.getRole());
            extraClaims.put("name", user.getName());
            extraClaims.put("email", user.getEmail());

            String jwtToken = jwtService.generateToken(extraClaims, user);

            return ResponseEntity.ok(AuthResponseBuilder.buildAuthResponse(jwtToken, user));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(ErrorResponseBuilder.buildErrorResponse(
                            e.getMessage(), HttpStatus.UNAUTHORIZED
                    ));
        }
    }

    @PostMapping("register")
    /**
     * Registers a new user, encodes password, and returns a JWT with user info.
     *
     * @param request registration payload with name, lastname, email, and password
     * @return 201 Created with token and user info, or 400 on validation/error
     */
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest request) {
        try {
            User user = authService.register(request);

            Map<String, Object> extraClaims = new HashMap<>();
            extraClaims.put("role", user.getRole());
            extraClaims.put("name", user.getName());
            extraClaims.put("email", user.getEmail());

            String jwtToken = jwtService.generateToken(extraClaims, user);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(AuthResponseBuilder.buildAuthResponse(jwtToken, user));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ErrorResponseBuilder.buildErrorResponse(
                            e.getMessage(), HttpStatus.BAD_REQUEST
                    ));
        }
    }
}
