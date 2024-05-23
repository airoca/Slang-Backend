package com.example.slang.repository;

import com.example.slang.model.Friend;
import com.example.slang.model.FriendId;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface FriendRepository extends JpaRepository<Friend, FriendId> {
    List<Friend> findByUserId(String userId);
}
