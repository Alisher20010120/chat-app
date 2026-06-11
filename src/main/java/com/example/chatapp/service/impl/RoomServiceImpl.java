package com.example.chatapp.service.impl;

import com.example.chatapp.dto.request.RoomRequest;
import com.example.chatapp.entity.Room;
import com.example.chatapp.entity.User;
import com.example.chatapp.exception.BadRequestException;
import com.example.chatapp.exception.ResourceNotFoundException;
import com.example.chatapp.repository.RoomRepository;
import com.example.chatapp.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;


    @Override
    public Room createRoom(RoomRequest roomRequest, User user) {
        if (roomRequest.getRoomName() == null || roomRequest.getRoomName().equals("")) {
            throw new BadRequestException("Room name is required");
        }
        if (roomRepository.existsRoomByRoomName(roomRequest.getRoomName())) {
            throw new BadRequestException("Room name is already in use");
        }
        Room room = new Room();
        room.setRoomName(roomRequest.getRoomName());
        room.getUsers().add(user);
        roomRepository.save(room);
        return room;
    }

    @Override
    public Object joinRoom(Long roomId, User user) {
        Room room = roomRepository.findById(roomId).orElseThrow(() -> new ResourceNotFoundException("Room not found"));
        boolean alreadyExists = room.getUsers().stream().anyMatch(u -> u.getId().equals(user.getId()));
        if (alreadyExists) {
            throw new BadRequestException("User already exists");
        }
        room.getUsers().add(user);
        roomRepository.save(room);
        return room;
    }

    @Override
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    @Override
    public Room getId(Long id) {
       return roomRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Room not found"));
    }

}
