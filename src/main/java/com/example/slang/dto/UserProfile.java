package com.example.slang.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class UserProfile {
    private String userId;
    private LocalDate date;
    private String name;
    private int animal;
    private int food;
    private int sports;
    private int korean;
    private int total;
    private int rank;
}
