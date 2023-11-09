package com.allways.domain.blog.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.allways.domain.user.entity.User;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Blog {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long blogSeq;

	@Column
	private String blogDescription;

	@Column
	private String blogName;

	@OneToOne
	@JoinColumn(name = "user_seq")
	private User user;



}
