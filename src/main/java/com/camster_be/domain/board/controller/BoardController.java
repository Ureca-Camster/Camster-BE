package com.camster_be.domain.board.controller;

import com.camster_be.domain.board.dto.request.BoardCreateRequest;
import com.camster_be.domain.board.dto.request.BoardUpdateRequest;
import com.camster_be.domain.board.entity.Board;
import com.camster_be.domain.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/studies")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;


    @PostMapping("/{studyId}/boards")
    public ResponseEntity<Board> createBoard(@PathVariable Long studyId,
                                             @RequestBody BoardCreateRequest request) {
        Board createdBoard = boardService.createBoard(studyId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBoard);
    }

    @GetMapping("/{studyId}/boards")
    public ResponseEntity<List<Board>> getAllBoards(@PathVariable Long studyId) {
        List<Board> boards = boardService.getAllBoards(studyId);
        return ResponseEntity.ok(boards);
    }

    @GetMapping("/{studyId}/boards/{boardId}")
    public ResponseEntity<Board> getBoardById(@PathVariable Long studyId,
                                              @PathVariable Long boardId) {
        return boardService.getBoardById(studyId, boardId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{studyId}/boards/{boardId}")
    public ResponseEntity<Void> deleteBoard(@PathVariable Long studyId,
                                            @PathVariable Long boardId) {
        boardService.deleteBoard(studyId, boardId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{studyId}/boards/{boardId}")
    public ResponseEntity<Void> updateBoard(@PathVariable Long studyId,
                                             @PathVariable Long boardId,
                                             @RequestBody BoardUpdateRequest request) {
        boardService.updateBoard(studyId, boardId, request);
        return ResponseEntity.ok().build();
    }
}