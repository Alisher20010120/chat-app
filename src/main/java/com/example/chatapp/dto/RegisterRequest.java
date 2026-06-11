package com.example.chatapp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest {
    @NotBlank(message = "UserName bo'sh bo'lishi mumkin emas")
    private String username;
    @NotBlank(message = "Password bo'sh bo'lishi mumkin emas")
    @Size(min = 6, message = "Password kamida 6 belgi")
    private String password;
}
