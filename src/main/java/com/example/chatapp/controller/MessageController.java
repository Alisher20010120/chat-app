package com.example.chatapp.controller;

import com.example.chatapp.entity.Messages;
import com.example.chatapp.entity.User;
import com.example.chatapp.service.MessagesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/message")
@RequiredArgsConstructor
public class MessageController {

    private final MessagesService messagesService;

    @GetMapping("/{roomId}")
    public ResponseEntity<List<Messages>> getMessages(
            @PathVariable Long roomId,
            @AuthenticationPrincipal User user) {
        return ResponseEntity.ok(messagesService.getMessages(roomId,user));
    }


}
