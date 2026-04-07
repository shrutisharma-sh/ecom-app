package com.shruti.ecom_back.controller;

import com.shruti.ecom_back.dto.AuthResponse;
import com.shruti.ecom_back.dto.LoginRequest;
import com.shruti.ecom_back.dto.RegisterRequest;
import com.shruti.ecom_back.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    // REGISTER
    @PostMapping("/register")
    public AuthResponse register(@RequestBody RegisterRequest request) {
        return authService.register(request);
    }
    // LOGIN
    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }

}
