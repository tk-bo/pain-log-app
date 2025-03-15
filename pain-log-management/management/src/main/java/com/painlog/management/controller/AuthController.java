package com.painlog.management.controller;

import com.painlog.management.dto.login.LoginRequest;
import com.painlog.management.dto.login.LoginResponse;
import com.painlog.management.domein.service.AuthenticationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
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
        return ResponseEntity.ok(new LoginResponse(token));
    }
}
