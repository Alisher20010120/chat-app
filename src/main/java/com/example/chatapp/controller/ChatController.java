package com.example.chatapp.controller;

import com.example.chatapp.dto.request.ChatMessageRequest;
import com.example.chatapp.entity.Messages;
import com.example.chatapp.entity.User;
import com.example.chatapp.repository.UserRepository;
import com.example.chatapp.service.MessagesService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.Optional;


@Controller
@RequiredArgsConstructor
public class ChatController {

    private final MessagesService messagesService;
    private final UserRepository userService;

    @MessageMapping("/chat.send/{roomId}")
    @SendTo("/topic/room.{roomId}")
    public Messages sendMessage(
            @DestinationVariable Long roomId,
            @Payload ChatMessageRequest message) {

        Optional<User> sender = userService.findByUsername(message.getUsername());

        return messagesService.sendMessage(
                roomId,
                message.getContent(),
                sender.orElse(null)
        );
    }
}