package com.example.chatapp.service;

import com.example.chatapp.entity.Messages;
import com.example.chatapp.entity.User;

import java.util.List;

public interface MessagesService {
    Messages sendMessage(Long roomId, String content, User sender);
    List<Messages> getMessages(Long roomId, User user);
}