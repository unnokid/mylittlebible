package com.example.mylittlebible.domain.board.util;

import com.example.mylittlebible.domain.board.dto.BoardInfoResponse;
import com.example.mylittlebible.domain.board.entity.Board;
import com.example.mylittlebible.domain.user.entity.User;

public class BoardConverter {

    public static Board toBoard(String title, String content, User user){
        Board board = new Board(
            title,
            content,
            user
        );
        return board;
    }

    public static BoardInfoResponse getInfoFromBoard(Board board){
        return BoardInfoResponse.builder()
            .id(board.getId())
            .title(board.getTitle())
            .content(board.getContent())
            .userId(board.getUser().getId())
            .build();
    }
}
