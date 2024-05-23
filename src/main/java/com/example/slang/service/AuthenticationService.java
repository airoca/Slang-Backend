package com.example.slang.service;

import com.example.slang.model.Point;
import com.example.slang.model.User;
import com.example.slang.repository.UserRepository;
import com.example.slang.repository.PointRepository;
import com.example.slang.jwt.JwtToken;
import com.example.slang.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private PointRepository pointRepository;

    // 회원 가입
    public User registerUser(User newUser) {
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        User savedUser = userRepository.save(newUser);

        // point 테이블에 새로운 사용자 추가 및 초기화
        Point newPoint = new Point();
        newPoint.setUserId(savedUser.getUserId());
        newPoint.setFood(0);
        newPoint.setAnimal(0);
        newPoint.setSports(0);
        newPoint.setTotal(0);
        pointRepository.save(newPoint);

        return savedUser;
    }

    // 로그인
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
}
