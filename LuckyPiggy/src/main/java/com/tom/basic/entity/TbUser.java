package com.tom.basic.entity;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;

import com.tom.basic.model.UserVO;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TbUser {

	public TbUser(UserVO vo) {
		userId = vo.getUser_id();
		userPw = vo.getUser_pw();
		userNick = vo.getUser_nick();
	}
	
    // 사용자 아이디
	@Id
    private String userId;

    // 사용자 비밀번호 
    private String userPw;

    // 사용자 이름 
    private String userName;

    // 사용자 이메일 
    private String userEmail;

    // 사용자 닉네임 
    private String userNick;

    // 사용자 사진 
    private String userPhoto;

    // 사용자 구분 
    private String userType;

    // 생성 일시
    @CreationTimestamp
    private Timestamp createdAt;
    
}
