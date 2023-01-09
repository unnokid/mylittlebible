package com.example.mylittlebible.domain.board.service;

import com.example.mylittlebible.domain.board.dto.BoardInfoResponse;
import com.example.mylittlebible.domain.board.dto.SaveRequest;
import com.example.mylittlebible.domain.board.dto.UpdateRequest;
import com.example.mylittlebible.domain.board.entity.Board;
import com.example.mylittlebible.domain.board.repository.BoardRepository;
import com.example.mylittlebible.domain.board.util.BoardConverter;
import com.example.mylittlebible.domain.user.entity.User;
import com.example.mylittlebible.domain.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Service
public class BoardService {

    private BoardRepository boardRepository;
    private UserRepository userRepository;

    public BoardService(
        BoardRepository boardRepository,
        UserRepository userRepository) {
        this.boardRepository = boardRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public void save(SaveRequest request){

        User user = userRepository.findById(request.getUserId())
            .orElseThrow(RuntimeException::new);

        boardRepository.save(BoardConverter.toBoard(request.getTitle(),request.getContent(),user));
    }

    public BoardInfoResponse getInfo(Long boardId){
        Board board = boardRepository.findById(boardId)
            .orElseThrow(RuntimeException::new);

        return BoardConverter.getInfoFromBoard(board);
    }

    @Transactional
    public void delete(Long boardId){
        Board board = boardRepository.findById(boardId)
            .orElseThrow(RuntimeException::new);

        boardRepository.delete(board);
    }

    @Transactional
    public void update(UpdateRequest request){
        Board board = boardRepository.findById(request.getBoardId())
            .orElseThrow(RuntimeException::new);

        board.changeTitle(request.getTitle());
        board.changeContent(request.getContent());
    }
}
