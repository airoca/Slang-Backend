package com.example.slang.controller;

import com.example.slang.model.User;
import com.example.slang.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    // 회원 가입
    @PostMapping("/register")
    public User registerUser(@RequestBody User newUser) {
        return authenticationService.registerUser(newUser);
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User loginDetails) {
        Map<String, Object> response = authenticationService.loginUser(loginDetails.getUserId(), loginDetails.getPassword());
        return ResponseEntity.ok(response);
    }
}

