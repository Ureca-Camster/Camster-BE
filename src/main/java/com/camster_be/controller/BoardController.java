package com.camster_be.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.camster_be.entity.Board;
import com.camster_be.service.BoardService;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/boards")  // 기본 경로 설정
@CrossOrigin("*")  // 모든 도메인에서의 요청 허용
public class BoardController {

    @Autowired
    private BoardService boardService;

    @PostMapping
    public Board createBoard(@RequestBody Board board) {
        return boardService.createBoard(board);
    }

    @GetMapping("/study/{studyId}")
    public List<Board> getAllBoards(@PathVariable("studyId") Long studyId) {
    	return boardService.getBoardsByStudyId(studyId);
    }

    @GetMapping("/{id}")
    public Optional<Board> getBoardById(@PathVariable Long id) {
        return boardService.getBoardById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteBoard(@PathVariable Long id) {
        boardService.deleteBoard(id);
    }
}





