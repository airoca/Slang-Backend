package com.example.slang.service;

import com.example.slang.model.User;
import com.example.slang.repository.UserRepository;
import com.example.slang.jwt.JwtToken;
import com.example.slang.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    //모든 회원 정보
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // 회원 가입
    public User registerUser(User newUser) {
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        return userRepository.save(newUser);
    }

    //로그인
    public Map<String, Object> loginUser(String id, String password) {
        User user = userRepository.findById(id)
                .filter(u -> passwordEncoder.matches(password, u.getPassword()))
                .orElseThrow(() -> new RuntimeException("Login Failed"));

        JwtToken jwtToken = jwtTokenProvider.generateToken(new UsernamePasswordAuthenticationToken(id, null));

        Map<String, Object> response = new HashMap<>();
        response.put("user", user);
        response.put("token", jwtToken);

        return response;
    }

    //특정 사용자 정보
    public Optional<User> getUserById(String id) {
        return userRepository.findById(id);
    }
}
