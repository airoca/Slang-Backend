package com.example.slang.service;

import com.example.slang.dto.UserProfile;
import com.example.slang.model.Point;
import com.example.slang.model.User;
import com.example.slang.repository.PointRepository;
import com.example.slang.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class PageService {

    @Autowired
    private PointRepository pointRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private static final String RANKING_KEY = "userRanking";

    @Bean
    public ApplicationRunner cacheUserRankingOnStartup() {
        return args -> {
            cacheUserRanking();
        };
    }

    public void cacheUserRanking() {
        List<Point> rankings = pointRepository.findAllByOrderByTotalDesc();
        redisTemplate.opsForValue().set(RANKING_KEY, rankings, 1, TimeUnit.HOURS);
    }

    // 캐시된 사용자 랭킹 정보 가져오기
    public List<Point> getUserRanking() {
        List<Point> rankings = (List<Point>) redisTemplate.opsForValue().get(RANKING_KEY);
        if (rankings == null) {
            rankings = pointRepository.findAllByOrderByTotalDesc();
            redisTemplate.opsForValue().set(RANKING_KEY, rankings, 1, TimeUnit.HOURS);
        }
        return rankings;
    }

    // 특정 사용자의 랭킹 가져오기
    public int getUserRanking(String userId) {
        List<Point> rankings = getUserRanking();
        for (int i = 0; i < rankings.size(); i++) {
            if (rankings.get(i).getUserId().equals(userId)) {
                return i + 1;
            }
        }
        return -1; // 사용자 랭킹을 찾지 못한 경우
    }

    // 특정 사용자의 프로필 정보 가져오기
    public UserProfile getUserProfile(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Point point = pointRepository.findById(userId).orElseThrow(() -> new RuntimeException("Point not found"));
        int rank = getUserRanking(userId);
        return new UserProfile(user.getUserId(), user.getDate(), user.getName(), point.getAnimal(), point.getFood(), point.getSports(), point.getKorean(), point.getTotal(), rank);
    }
}
