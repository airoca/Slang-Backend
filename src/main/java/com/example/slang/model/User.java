package com.example.slang.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Getter
@Setter
public class User {
    @Id
    private String userId;

    private String password;

    private String name;

    private LocalDate date;
}
