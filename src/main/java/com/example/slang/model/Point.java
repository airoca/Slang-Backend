package com.example.slang.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Point {
    @Id
    private String userId;
    private int food;
    private int animal;
    private int sports;
    private int total;
}
