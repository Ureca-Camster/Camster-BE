package com.camster_be.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.camster_be.entity.Board;
import com.camster_be.repository.BoardRepository;
import java.util.List;
import java.util.Optional;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    public Board createBoard(Board board) {
        return boardRepository.save(board);
    }

 // studyId에 해당하는 게시물 목록만 반환
    public List<Board> getBoardsByStudyId(Long studyId) {
        return boardRepository.findByStudyId(studyId);
    }

    public Optional<Board> getBoardById(Long id) {
        return boardRepository.findById(id);
    }

    public void deleteBoard(Long id) {
        boardRepository.deleteById(id);
    }
}

