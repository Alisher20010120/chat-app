package com.example.chatapp.controller;


import com.example.chatapp.dto.request.RoomRequest;
import com.example.chatapp.entity.Room;
import com.example.chatapp.entity.User;
import com.example.chatapp.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/room")
@RequiredArgsConstructor
public class RoomController {
    private final RoomService roomService;
    private final SimpMessagingTemplate simpMessagingTemplate;

    @PostMapping("/create")
    public ResponseEntity<Room>createRoom(@RequestBody RoomRequest roomRequest,@AuthenticationPrincipal User user){
        Room room = roomService.createRoom(roomRequest, user);
        simpMessagingTemplate.convertAndSend("/topic/rooms.new",room);
        return ResponseEntity.ok(room);
    }

    @PostMapping("/{roomId}/join")
    public ResponseEntity<?>joinRoom(@PathVariable Long roomId, @AuthenticationPrincipal User user){
        return ResponseEntity.ok(roomService.joinRoom(roomId,user));
    }


    @GetMapping("/getAll")
    public ResponseEntity<List<Room>> getAllRooms(){
        return ResponseEntity.ok(roomService.getAllRooms());
    }

    @GetMapping("/{id}/getId")
    public ResponseEntity<Room> getRoomById(@PathVariable Long id){
        return ResponseEntity.ok(roomService.getId(id));
    }

}
