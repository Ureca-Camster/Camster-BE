package com.camster_be.domain.board.service;

import com.camster_be.domain.board.dto.request.CommentRequest;
import com.camster_be.domain.board.entity.Board;
import com.camster_be.domain.board.entity.Comment;
import com.camster_be.domain.board.repository.BoardRepository;
import com.camster_be.domain.board.repository.CommentRepository;
import com.camster_be.domain.member.entity.Member;
import com.camster_be.domain.member.repository.MemberRepository;
import com.camster_be.domain.util.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{
    
    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;

    @Override
    public Comment createComment(Long boardId, CommentRequest request) {
        Long memberId = SecurityUtils.getMemberId();
        Member member = memberRepository.findById(memberId).orElseThrow();
        Comment comment = new Comment(request, memberId, boardId, member.getNickname());
        return commentRepository.save(comment);
    }

    @Override
    public List<Comment> getAllComments(Long boardId) {
        return commentRepository.findAllByBoardId(boardId);
    }

    @Override
    public void deleteComment(Long boardId, Long commentId) {
        commentRepository.deleteById(commentId);
    }

    @Override
    public void updateComment(Long boardId, Long commentId, CommentRequest request) {
        Comment comment = commentRepository.findById(commentId).orElseThrow();
        comment.updateComment(request);
    }
}
