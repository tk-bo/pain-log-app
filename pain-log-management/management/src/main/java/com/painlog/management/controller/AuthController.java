package com.painlog.management.controller;

import com.painlog.management.dto.login.LoginRequest;
import com.painlog.management.dto.login.LoginResponse;
import com.painlog.management.dto.login.RegisterRequest;
import com.painlog.management.dto.login.RegisterResponse;
import com.painlog.management.domein.service.AuthenticationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        String token = authenticationService.authenticate(loginRequest.getUsername(), loginRequest.getPassword());
        if (token == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body("Invalid username or password");
        }

        // username と JWT トークンをレスポンスに含める
        LoginResponse response = new LoginResponse(token, loginRequest.getUsername());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest) {
        try {
            authenticationService.register(registerRequest.getUsername(), registerRequest.getPassword());
            // RegisterResponse の引数付きコンストラクタを利用
            RegisterResponse response = new RegisterResponse("register success!");
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Registration failed: " + e.getMessage());
        }
    }

}
