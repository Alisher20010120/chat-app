package com.example.chatapp.controller;

import com.example.chatapp.dto.AuthResponse;
import com.example.chatapp.dto.RegisterRequest;
import com.example.chatapp.dto.request.LoginRequest;
import com.example.chatapp.service.Authservice;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/auth")
@RestController
@RequiredArgsConstructor
@Tag(name = "Autentifikatsiya", description = "Tizimga kirish va ro'yxatdan o'tish (Login/Register) API-lari")
public class AuthController {

    private final Authservice authservice;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> registerUser(@RequestBody RegisterRequest dto) {
        return ResponseEntity.ok(authservice.register(dto));
    }

    @PostMapping ("/login")
    public ResponseEntity<AuthResponse>loginUser(@Valid @RequestBody RegisterRequest dto) {
        return ResponseEntity.ok(authservice.login(dto));
    }



}
