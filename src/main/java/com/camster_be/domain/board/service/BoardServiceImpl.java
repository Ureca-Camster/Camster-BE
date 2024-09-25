package com.camster_be.domain.board.service;

import com.camster_be.domain.board.dto.request.BoardCreateRequest;
import com.camster_be.domain.board.dto.request.BoardUpdateRequest;
import com.camster_be.domain.board.entity.Board;
import com.camster_be.domain.board.repository.BoardRepository;
import com.camster_be.domain.member.repository.MemberRepository;
import com.camster_be.domain.study.repository.StudyRepository;
import com.camster_be.domain.util.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;

    @Override
    public Board createBoard(Long studyId, BoardCreateRequest request) {
        Long memberId = SecurityUtils.getMemberId();
        Board board = new Board(request, memberId, studyId);
        boardRepository.save(board);
        return board;
    }

    @Override
    public List<Board> getAllBoards(Long studyId) {
        return boardRepository.findAllByStudyId(studyId);
    }

    @Override
    public Optional<Board> getBoardById(Long studyId, Long boardId) {
        return boardRepository.findById(boardId);
    }

    @Override
    public void deleteBoard(Long studyId, Long boardId) {
        boardRepository.deleteById(boardId);
    }

    @Override
    public void updateBoard(Long studyId, Long boardId, BoardUpdateRequest request) {
        Board board = boardRepository.findById(boardId).orElseThrow();
        board.updateBoard(request);
    }
}
