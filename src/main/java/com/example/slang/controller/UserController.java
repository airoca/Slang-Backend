package com.example.slang.controller;

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

    //모든 사용자
    @GetMapping("/users")
    public List<User> getUsers(){
        return userService.getAllUsers();
    }

    //회원 가입
    @PostMapping("/register")
    public User registerUser(@RequestBody User newUser) {
        return userService.registerUser(newUser);
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User loginDetails) {
        Map<String, Object> response = userService.loginUser(loginDetails.getId(), loginDetails.getPassword());
        return ResponseEntity.ok(response);
    }

    //특정 사용자 정보
    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable("id") String id) {
        return userService.getUserById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

}
