package com.example.mylittlebible.domain.board.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
@NoArgsConstructor
@Builder
public class BoardInfoResponse {
    private Long id;
    private String title;
    private String content;
    private Long userId;
}
