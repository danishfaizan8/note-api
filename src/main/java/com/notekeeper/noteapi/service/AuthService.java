package com.notekeeper.noteapi.service;

import com.notekeeper.noteapi.dto.AuthResponse;
import com.notekeeper.noteapi.dto.LoginRequest;
import com.notekeeper.noteapi.dto.RegisterRequest;
import com.notekeeper.noteapi.entity.User;
import com.notekeeper.noteapi.repository.UserRepository;
import com.notekeeper.noteapi.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthResponse register(RegisterRequest request) {
        User user = new User(request.getUsername(), passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);

        String token = jwtService.generateToken(user.getUsername());
        return new AuthResponse(token);
    }

    public AuthResponse login(LoginRequest request) {
        Authentication auth = new UsernamePasswordAuthenticationToken(
                request.getUsername(), request.getPassword());
        authenticationManager.authenticate(auth);

        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        String token = jwtService.generateToken(user.getUsername());
        return new AuthResponse(token);
    }
}
