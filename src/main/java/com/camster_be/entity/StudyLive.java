package com.camster_be.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "StudyLive")
public class StudyLive {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studyLiveId;

    private Long studyId;

    // Getters and setters
	public Long getStudyLiveId() {
		return studyLiveId;
	}

	public void setStudyLiveId(Long studyLiveId) {
		this.studyLiveId = studyLiveId;
	}

	public Long getStudyId() {
		return studyId;
	}

	public void setStudyId(Long studyId) {
		this.studyId = studyId;
	}   
}

