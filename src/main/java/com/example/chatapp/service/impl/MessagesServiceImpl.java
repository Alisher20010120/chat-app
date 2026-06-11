package com.example.chatapp.service.impl;

import com.example.chatapp.entity.Messages;
import com.example.chatapp.entity.Room;
import com.example.chatapp.entity.User;
import com.example.chatapp.exception.ResourceNotFoundException;
import com.example.chatapp.repository.MessagesRepository;
import com.example.chatapp.repository.RoomRepository;
import com.example.chatapp.service.MessagesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessagesServiceImpl implements MessagesService {

    private final RoomRepository roomRepository;
    private final MessagesRepository messagesRepository;

    @Override
    public Messages sendMessage(Long roomId, String content, User sender) {
        Room room = roomRepository.findById(roomId).orElseThrow(() -> new ResourceNotFoundException("Room not found"));
        Messages messages = Messages.builder().content(content).room(room).user(sender).build();
        return messagesRepository.save(messages);
    }


    @Override
    public List<Messages> getMessages(Long roomId, User user) {
        Room room = roomRepository.findById(roomId).orElseThrow(() -> new ResourceNotFoundException("Room not found"));
        return messagesRepository.findByRoomOrderByCreatedAtAsc(room);
    }
}
