package com.example.chatapp.service;

import com.example.chatapp.dto.AuthResponse;
import com.example.chatapp.dto.RegisterRequest;
import com.example.chatapp.dto.request.LoginRequest;
import org.jspecify.annotations.Nullable;

public interface Authservice {
     AuthResponse register(RegisterRequest dto);

     AuthResponse login(RegisterRequest dto);
}
