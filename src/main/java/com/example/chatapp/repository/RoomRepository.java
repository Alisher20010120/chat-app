package com.example.chatapp.repository;

import com.example.chatapp.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
    boolean existsRoomByRoomName(String roomName);
}