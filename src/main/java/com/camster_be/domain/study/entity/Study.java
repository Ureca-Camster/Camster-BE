package com.camster_be.domain.study.entity;

import com.camster_be.domain.study.dto.request.StudyCreateRequest;
import com.camster_be.domain.study.dto.request.StudyUpdateRequest;
import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

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
    private Long masterId;
    private Boolean isLive;

    public Study(StudyCreateRequest request, Long masterId) {
        this.studyName = request.studyName();
        this.description = request.description();
        this.emoji = request.emoji();
        this.isPublic = request.isPublic();
        this.studyPassword = request.studyPassword();
        this.masterId = masterId;
        this.isLive = false;
    }

    public void updateStudy(StudyUpdateRequest request) {
        if (!StringUtils.hasText(request.studyName())) {
            this.studyName = request.studyName();
        }

        if (!StringUtils.hasText(request.description())) {
            this.description = request.description();
        }

        if (!StringUtils.hasText(request.emoji())) {
            this.emoji = request.emoji();
        }

        if (!StringUtils.hasText(request.studyPassword())) {
            this.studyPassword = request.studyPassword();
            this.isPublic = request.isPublic();
        }
    }
}
