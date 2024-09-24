package com.camster_be.domain.board.service;

import com.camster_be.domain.board.dto.request.BoardCreateRequest;
import com.camster_be.domain.board.dto.request.BoardUpdateRequest;
import com.camster_be.domain.board.entity.Board;

import java.util.List;
import java.util.Optional;

public interface BoardService {

    Board createBoard(Long studyId, BoardCreateRequest request);

    List<Board> getAllBoards(Long studyId);

    Optional<Board> getBoardById(Long studyId, Long id);

    void deleteBoard(Long studyId, Long id);

    void updateBoard(Long studyId, Long id, BoardUpdateRequest request);

}

