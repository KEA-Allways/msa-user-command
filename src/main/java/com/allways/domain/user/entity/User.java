package com.allways.domain.user.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.allways.common.EntityDate;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Getter
public class User extends EntityDate {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userSeq;

	@Column(nullable = false, unique = true)
	private String userId;

	@Column(nullable = false)
	private String password;

	@Column(nullable = false, unique = true)
	private String nickname;

	@Column(nullable = false, unique = true)
	private String email;
	//이미지 api 호출?
	@Column
	private String profileImgSeq;


	public User(String userId,String password,String nickname,String email,String profileImgSeq){
		this.userId=userId;
		this.password=password;
		this.nickname=nickname;
		this.email=email;
		this.profileImgSeq=profileImgSeq;
	}


}
