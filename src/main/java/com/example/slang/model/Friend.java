package com.example.slang.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@IdClass(FriendId.class)
public class Friend {
    @Id
    private String userId;
    @Id
    private String friendId;
}
