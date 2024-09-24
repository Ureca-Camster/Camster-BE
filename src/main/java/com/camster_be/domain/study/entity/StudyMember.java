package com.camster_be.domain.study.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "StudyMember")
@Getter
@NoArgsConstructor
public class StudyMember {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "member_id")
	private Long memberId;

	@Column(name = "study_id")
	private Long studyId;

	public StudyMember(Long memberId, Long studyId) {
		this.memberId = memberId;
		this.studyId = studyId;
	}
}