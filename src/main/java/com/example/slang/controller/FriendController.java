package com.example.slang.controller;

import com.example.slang.model.Friend;
import com.example.slang.model.FriendRequest;
import com.example.slang.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class FriendController {

    @Autowired
    private FriendService friendService;

    @PostMapping("/friend/request")
    public ResponseEntity<?> sendFriendRequest(@RequestBody Map<String, String> request) {
        String senderId = request.get("senderId");
        String receiverId = request.get("receiverId");
        friendService.sendFriendRequest(senderId, receiverId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/friend/accept")
    public ResponseEntity<?> acceptFriendRequest(@RequestBody Map<String, String> request) {
        String senderId = request.get("senderId");
        String receiverId = request.get("receiverId");
        friendService.acceptFriendRequest(senderId, receiverId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/friend/decline")
    public ResponseEntity<?> declineFriendRequest(@RequestBody Map<String, String> request) {
        String senderId = request.get("senderId");
        String receiverId = request.get("receiverId");
        friendService.declineFriendRequest(senderId, receiverId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/friend/requests/sent/{userId}")
    public List<FriendRequest> getSentFriendRequests(@PathVariable String userId) {
        return friendService.getSentFriendRequests(userId);
    }

    @GetMapping("/friend/requests/received/{userId}")
    public List<FriendRequest> getReceivedFriendRequests(@PathVariable String userId) {
        return friendService.getReceivedFriendRequests(userId);
    }

    @GetMapping("/friends/{userId}")
    public List<Friend> getFriends(@PathVariable String userId) {
        return friendService.getFriends(userId);
    }
}
