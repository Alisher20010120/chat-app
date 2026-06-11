package com.example.chatapp.service.impl;

import com.example.chatapp.dto.AuthResponse;
import com.example.chatapp.dto.RegisterRequest;
import com.example.chatapp.dto.request.LoginRequest;
import com.example.chatapp.entity.Role;
import com.example.chatapp.entity.User;
import com.example.chatapp.exception.BadRequestException;
import com.example.chatapp.repository.UserRepository;
import com.example.chatapp.service.Authservice;
import com.example.chatapp.utils.JwtUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements Authservice {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;


    @Override
    public AuthResponse register(RegisterRequest dto) {
        if (userRepository.existsByUsername(dto.getUsername())) {
            throw new BadRequestException("Username is already in use");
        }
        User user = User.builder()
                .username(dto.getUsername())
                .password(passwordEncoder.encode(dto.getPassword()))
                .role(Role.USER)
                .build();
        userRepository.save(user);
        return AuthResponse.builder().token(jwtUtil.generateToken(dto.getUsername())).build();
    }

    @Override
    public AuthResponse login(RegisterRequest dto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword())
        );
        return AuthResponse.builder().token(jwtUtil.generateToken(dto.getUsername())).build();
    }


}
