package com.example.slang.dto;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GPTResponse {
    private List<com.example.slang.dto.GPTResponse.Choice> choices;
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Choice {
        private int index;
        private Message message;

    }
}
