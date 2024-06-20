package com.example.slang.repository;

import com.example.slang.model.PointRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PointRecordRepository extends JpaRepository<PointRecord, Long> {
    List<PointRecord> findByUserId(String userId);
}


