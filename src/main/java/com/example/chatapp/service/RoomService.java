package com.example.chatapp.service;

import com.example.chatapp.dto.request.RoomRequest;
import com.example.chatapp.entity.Room;
import com.example.chatapp.entity.User;
import org.jspecify.annotations.Nullable;

import java.util.List;

public interface RoomService {
     Room createRoom(RoomRequest roomRequest, User user);

     Object joinRoom(Long roomId, User user);

     List<Room> getAllRooms();

     Room getId(Long id);
}
