package com.example.mylittlebible.domain.board.controller;

import com.example.mylittlebible.domain.board.dto.BoardInfoResponse;
import com.example.mylittlebible.domain.board.dto.SaveBoardRequest;
import com.example.mylittlebible.domain.board.dto.UpdateBoardRequest;
import com.example.mylittlebible.domain.board.service.BoardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/board")
public class BoardController {

    private BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }


    @PostMapping("/save")
    public ResponseEntity save(@RequestBody SaveBoardRequest request){

        boardService.save(request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{boardId}")
    public ResponseEntity delete(@PathVariable Long boardId){

        boardService.delete(boardId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/update")
    public ResponseEntity update(@RequestBody UpdateBoardRequest request){
        boardService.update(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{boardId}")
    public ResponseEntity<BoardInfoResponse> getInfoBoard(@PathVariable Long boardId){
        BoardInfoResponse board = boardService.getInfo(boardId);

        return ResponseEntity.ok(board);
    }
}
