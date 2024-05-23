package com.example.slang.repository;

import com.example.slang.model.FriendRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface FriendRequestRepository extends JpaRepository<FriendRequest, Long> {
    List<FriendRequest> findBySenderId(String senderId);
    List<FriendRequest> findByReceiverIdAndStatus(String receiverId, FriendRequest.RequestStatus status);
    Optional<FriendRequest> findBySenderIdAndReceiverId(String senderId, String receiverId);
}
