package com.camster_be.domain.board.controller;

import com.camster_be.domain.board.dto.request.CommentRequest;
import com.camster_be.domain.board.entity.Comment;
import com.camster_be.domain.board.service.BoardServiceImpl;
import com.camster_be.domain.board.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/boards")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/{boardId}/comments")
    public ResponseEntity<Comment> createComment(@PathVariable Long boardId,
                                                 @RequestBody CommentRequest request) {
        Comment createComment = commentService.createComment(boardId, request);
        return ResponseEntity.status(CREATED).body(createComment);
    }

    @GetMapping("/{boardId}/comments")
    public ResponseEntity<List<Comment>> getAllComments(@PathVariable Long boardId) {
        List<Comment> comments = commentService.getAllComments(boardId);
        return ResponseEntity.ok(comments);
    }

    @DeleteMapping("/{boardId}/comments/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long boardId,
                                              @PathVariable Long commentId) {
        commentService.deleteComment(boardId, commentId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{boardId}/comments/{commentId}")
    public ResponseEntity<Void> updateComment(@PathVariable Long boardId,
                                              @PathVariable Long commentId,
                                              @RequestBody CommentRequest request) {
        commentService.updateComment(boardId, commentId, request);
        return ResponseEntity.ok().build();
    }
}
