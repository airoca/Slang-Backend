package com.example.slang.repository;

import com.example.slang.model.Point;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PointRepository extends JpaRepository<Point, String> {
    List<Point> findAllByOrderByTotalDesc();
}
