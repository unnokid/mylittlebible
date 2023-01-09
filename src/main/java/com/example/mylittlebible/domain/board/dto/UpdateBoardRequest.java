package com.example.mylittlebible.domain.board.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpdateBoardRequest {
    private Long boardId;
    private String title;
    private String content;
    private Long userId;

}
