package com.camster_be.domain.board.entity;

import java.time.LocalDate;

import com.camster_be.domain.board.dto.request.CommentRequest;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

@Entity
@Table(name = "Comment")
@Getter
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "comment_id")
    private Long id;

    private String content;
    private LocalDate createdDate;
    private Long boardId;
    private Long memberId;
    private String nickname;

    public Comment(CommentRequest request, Long boardId, Long memberId, String nickname) {
        this.content = request.content();
        this.createdDate = LocalDate.now();
        this.boardId = boardId;
        this.memberId = memberId;
        this.nickname = nickname;
    }

    public void updateComment(CommentRequest request) {
        if (StringUtils.hasText(request.content())) {
            this.content = request.content();
        }
    }
}
