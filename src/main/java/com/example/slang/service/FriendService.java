package com.example.slang.service;

import com.example.slang.model.Friend;
import com.example.slang.model.FriendRequest;
import com.example.slang.repository.FriendRepository;
import com.example.slang.repository.FriendRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class FriendService {

    @Autowired
    private FriendRequestRepository friendRequestRepository;

    @Autowired
    private FriendRepository friendRepository;

    // 새로운 친구 요청 생성
    public void sendFriendRequest(String senderId, String receiverId) {
        FriendRequest friendRequest = new FriendRequest();
        friendRequest.setSenderId(senderId);
        friendRequest.setReceiverId(receiverId);
        friendRequest.setStatus(FriendRequest.RequestStatus.PENDING);
        friendRequestRepository.save(friendRequest);
    }

    // 친구 요청 수락
    public void acceptFriendRequest(String senderId, String receiverId) {
        FriendRequest friendRequest = friendRequestRepository.findBySenderIdAndReceiverId(senderId, receiverId)
                .orElseThrow(() -> new RuntimeException("Friend request not found"));
        friendRequest.setStatus(FriendRequest.RequestStatus.ACCEPTED);
        friendRequestRepository.save(friendRequest);

        // 양방향 친구 관계 생성
        Friend friend1 = new Friend();
        friend1.setUserId(senderId);
        friend1.setFriendId(receiverId);
        friendRepository.save(friend1);

        Friend friend2 = new Friend();
        friend2.setUserId(receiverId);
        friend2.setFriendId(senderId);
        friendRepository.save(friend2);
    }

    // 친구 요청 거절
    public void declineFriendRequest(String senderId, String receiverId) {
        FriendRequest friendRequest = friendRequestRepository.findBySenderIdAndReceiverId(senderId, receiverId)
                .orElseThrow(() -> new RuntimeException("Friend request not found"));
        friendRequest.setStatus(FriendRequest.RequestStatus.DECLINED);
        friendRequestRepository.save(friendRequest);
    }

    // 보낸 친구 요청 목록 조회
    public List<FriendRequest> getSentFriendRequests(String userId) {
        return friendRequestRepository.findBySenderId(userId);
    }

    // 받은 친구 요청 목록 조회
    public List<FriendRequest> getReceivedFriendRequests(String userId) {
        return friendRequestRepository.findByReceiverIdAndStatus(userId, FriendRequest.RequestStatus.PENDING);
    }

    // 친구 목록 조회
    public List<Friend> getFriends(String userId) {
        return friendRepository.findByUserId(userId);
    }
}
