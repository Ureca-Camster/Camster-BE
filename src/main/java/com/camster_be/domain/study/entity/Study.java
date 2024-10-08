package com.camster_be.domain.study.entity;

import com.camster_be.domain.member.entity.Member;
import com.camster_be.domain.study.dto.request.StudyCreateRequest;
import com.camster_be.domain.study.dto.request.StudyUpdateRequest;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "Study")
@NoArgsConstructor
public class Study {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="study_id")
	private Long id;

    private String studyName;
    private String description;
    // columnDefinition을 사용하여 emoji 컬럼의 charset과 collation을 설정
    @Column(columnDefinition = "VARCHAR(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci")
    private String emoji;
    private Boolean isPublic;
    private String studyPassword;
    private Long leaderId;

    public Study(StudyCreateRequest request, Long leaderId) {
        this.studyName = request.studyName();
        this.description = request.description();
        this.emoji = request.emoji();
        this.isPublic = request.isPublic();
        this.studyPassword = request.studyPassword();
        this.leaderId = leaderId;
    }

    public void updateStudy(StudyUpdateRequest request) {
        if (StringUtils.hasText(request.studyName())) {
            this.studyName = request.studyName();
        }

        if (StringUtils.hasText(request.description())) {
            this.description = request.description();
        }

        if (StringUtils.hasText(request.emoji())) {
            this.emoji = request.emoji();
        }

        this.isPublic = request.isPublic();
        if (!request.isPublic() && StringUtils.hasText(request.studyPassword())) {
            this.studyPassword = request.studyPassword();
        } else if (request.isPublic()) {
            this.studyPassword = null;  // 공개 스터디인 경우 비밀번호를 null로 설정
        }
    }
}
