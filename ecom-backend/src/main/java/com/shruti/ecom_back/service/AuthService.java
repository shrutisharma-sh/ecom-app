package com.shruti.ecom_back.service;

import com.shruti.ecom_back.dto.AuthResponse;
import com.shruti.ecom_back.dto.LoginRequest;
import com.shruti.ecom_back.dto.RegisterRequest;
import com.shruti.ecom_back.model.User;
import com.shruti.ecom_back.repository.UserRepository;
import com.shruti.ecom_back.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    // REGISTER
    public AuthResponse register(RegisterRequest request) {

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        User user = User.builder()
                .email(request.getEmail())
                .name(request.getName())
                .password(passwordEncoder.encode(request.getPassword()))
                .role("ROLE_" + request.getRole())
                .build();

        userRepository.save(user);

        String token = jwtUtil.generateToken(user.getEmail(), user.getRole());

        return new AuthResponse(token);
    }
    // LOGIN
    public AuthResponse login(LoginRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        String token = jwtUtil.generateToken(user.getEmail(), user.getRole());

        return new AuthResponse(token);
    }
}
