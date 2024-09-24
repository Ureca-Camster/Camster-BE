package com.camster_be.domain.board.entity;

import java.util.Date;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "Comment")
@Getter
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "comment_id")
    private Long id;

    private String content;
    private Date createdDate;
    private Long boardId;
    private Long memberId;

}
