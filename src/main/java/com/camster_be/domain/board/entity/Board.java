package com.camster_be.domain.board.entity;

import java.time.LocalDate;
import com.camster_be.domain.board.dto.request.BoardCreateRequest;
import com.camster_be.domain.board.dto.request.BoardUpdateRequest;
import com.camster_be.domain.member.entity.Member;
import com.camster_be.domain.study.entity.Study;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

@Entity
@Table(name = "Board")
@Getter
@NoArgsConstructor
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "board_id")
    private Long id;

    private String title;
    private String content;
    private LocalDate createDate;
    private Long memberId;
    private Long studyId;
    private String nickname;

    public Board(BoardCreateRequest request, Long memberId, Long studyId, String nickname) {
        this.title = request.title();
        this.content = request.content();
        this.createDate = LocalDate.now();
        this.memberId = memberId;
        this.studyId = studyId;
        this.nickname = nickname;
    }

    public void updateBoard(BoardUpdateRequest request) {
        if (!StringUtils.hasText(request.title())) {
            this.title = request.title();
        }
        if (!StringUtils.hasText(request.content())) {
            this.content = request.content();
        }
    }
}
