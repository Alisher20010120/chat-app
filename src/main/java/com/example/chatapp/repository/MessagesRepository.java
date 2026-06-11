package com.example.chatapp.repository;

import com.example.chatapp.entity.Messages;
import com.example.chatapp.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessagesRepository extends JpaRepository<Messages, Long> {
    List<Messages> findByRoomOrderByCreatedAtAsc(Room room); // ✅ qo'sh

}