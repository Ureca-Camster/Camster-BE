package com.camster_be.domain.member.entity;

import com.camster_be.domain.member.dto.request.MemberUpdateRequest;
import com.camster_be.domain.study.entity.StudyMember;
import io.micrometer.common.util.StringUtils;
import io.netty.util.internal.StringUtil;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "Member")
@NoArgsConstructor
public class Member {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "member_id")
	private Long id;

	private String email;
	private String nickname;
	private String memberPassword;
	private Integer goalTime;
	private Integer todayTime;
	private String role;

	public Member(String email, String nickname, Integer goalTime, String memberPassword) {
		this.email = email;
		this.nickname = nickname;
		this.goalTime = goalTime;
		this.memberPassword = memberPassword;
		this.todayTime = 0;
		this.role = "ROLE_USER";
	}

	public void updateMember(String nickname, String password, Integer goalTime) {
		// username 업데이트
		if (!StringUtils.isBlank(nickname)) {
			this.nickname = nickname;
		}

		// 비밀번호 업데이트
		if (!StringUtils.isBlank(password)) {
			this.memberPassword = password;
		}

		// goalTime 업데이트
		if (goalTime != null && goalTime > 0) {
			this.goalTime = goalTime;
		}
	}

	public void updateTodayTime(Integer time) {
		this.todayTime += time;
	}
}
