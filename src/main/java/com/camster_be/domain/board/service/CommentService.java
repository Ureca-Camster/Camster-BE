package com.camster_be.domain.board.service;

import com.camster_be.domain.board.dto.request.CommentRequest;
import com.camster_be.domain.board.entity.Comment;

import java.util.List;

public interface CommentService {
    Comment createComment(Long boardId, CommentRequest request);

    List<Comment> getAllComments(Long boardId);

    void deleteComment(Long boardId, Long commentId);

    void updateComment(Long boardId, Long commentId, CommentRequest request);
}
