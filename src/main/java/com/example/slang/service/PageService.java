package com.example.slang.service;

import com.example.slang.model.Point;
import com.example.slang.repository.PointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PageService {

    @Autowired
    private PointRepository pointRepository;

    // 사용자 랭킹 정보
    public List<Point> getUserRanking() {
        return pointRepository.findAllByOrderByTotalDesc();
    }
}
