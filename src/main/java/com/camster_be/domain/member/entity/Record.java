package com.camster_be.domain.member.entity;

import java.util.Date;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
@Table(name = "Record")
public class Record {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "record_id")
    private Long id;
    
    private Date recordDate;
    private Integer studyTime;

	@ManyToOne
	@JoinColumn(name = "member_id")
    private Member member;
}
