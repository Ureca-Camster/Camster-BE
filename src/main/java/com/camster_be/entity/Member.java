package com.camster_be.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Member")
public class Member {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long memberId;
	
	private String nickname;
	private String memberPassword;
	private Integer goalTime;
	private Integer todayTime;
	
	//Getters and Setters
	public Long getMemberId() {
		return memberId;
	}
	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getMemberPassword() {
		return memberPassword;
	}
	public void setMemberPassword(String memberPassword) {
		this.memberPassword = memberPassword;
	}
	public Integer getGoalTime() {
		return goalTime;
	}
	public void setGoalTime(Integer goalTime) {
		this.goalTime = goalTime;
	}
	public Integer getTodayTime() {
		return todayTime;
	}
	public void setTodayTime(Integer todayTime) {
		this.todayTime = todayTime;
	}
}
