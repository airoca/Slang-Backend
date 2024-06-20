package com.example.slang.controller;

import com.example.slang.model.PointRecord;
import com.example.slang.model.User;
import com.example.slang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    // 모든 사용자
    @GetMapping("/users")
    public List<User> getUsers(){
        return userService.getAllUsers();
    }

    // 특정 사용자 정보
    @GetMapping("/user/{userId}")
    public User getUserById(@PathVariable("userId") String userId) {
        return userService.getUserById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    // 학습 후 포인트 업데이트 및 포인트 기록 반환
    @PostMapping("/updatePoint")
    public ResponseEntity<List<PointRecord>> updatePoint(@RequestBody Map<String, Object> request) {
        String userId = (String) request.get("userId");
        String category = (String) request.get("category");
        int point = (int) request.get("point");
        userService.updatePoint(userId, category, point);
        List<PointRecord> pointRecords = userService.getPointRecordsByUserId(userId);
        return ResponseEntity.ok(pointRecords);
    }
}
