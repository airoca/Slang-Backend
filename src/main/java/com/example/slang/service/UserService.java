package com.example.slang.service;

import com.example.slang.model.Point;
import com.example.slang.model.PointRecord;
import com.example.slang.model.User;
import com.example.slang.repository.UserRepository;
import com.example.slang.repository.PointRepository;
import com.example.slang.repository.PointRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PointRepository pointRepository;

    @Autowired
    private PointRecordRepository pointRecordRepository;

    // 모든 회원 정보
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // 특정 사용자 정보
    public Optional<User> getUserById(String userId) {
        return userRepository.findById(userId);
    }

    // 사용자 포인트 업데이트
    public void updatePoint(String userId, String category, int point) {
        Point pointEntity = pointRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Point record not found"));

        switch (category) {
            case "food":
                pointEntity.setFood(point);
                break;
            case "animal":
                pointEntity.setAnimal(point);
                break;
            case "sports":
                pointEntity.setSports(point);
                break;
            default:
                throw new IllegalArgumentException("Invalid category: " + category);
        }

        int total = pointEntity.getFood() + pointEntity.getAnimal() + pointEntity.getSports();
        pointEntity.setTotal(total);

        pointRepository.save(pointEntity);

        // 포인트 업데이트 기록 저장
        PointRecord pointRecord = new PointRecord();
        pointRecord.setUserId(userId);
        pointRecord.setCategory(category);
        pointRecord.setPoint(point);
        pointRecord.setDate(LocalDate.now());
        pointRecordRepository.save(pointRecord);
    }
}
