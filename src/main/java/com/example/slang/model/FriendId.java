package com.example.slang.model;

import java.io.Serializable;
import java.util.Objects;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FriendId implements Serializable {
    private String userId;
    private String friendId;

    public FriendId() {}

    public FriendId(String userId, String friendId) {
        this.userId = userId;
        this.friendId = friendId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FriendId friendId1 = (FriendId) o;
        return userId.equals(friendId1.userId) && friendId.equals(friendId1.friendId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, friendId);
    }
}
