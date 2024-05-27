package com.example.slang.controller;

import com.example.slang.model.Point;
import com.example.slang.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PageController {

    @Autowired
    private PageService pageService;

    // 사용자 랭킹
    @GetMapping("/rank")
    public List<Point> getRanking() {
        return pageService.getUserRanking();
    }

    // 특정 사용자의 랭킹 가져오기
    @GetMapping("/rank/{userId}")
    public int getUserRanking(@PathVariable("userId") String userId) {
        return pageService.getUserRanking(userId);
    }
}
