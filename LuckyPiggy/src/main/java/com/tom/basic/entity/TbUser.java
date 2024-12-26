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
		user_id = vo.getUser_id();
		user_pw = vo.getUser_pw();
		user_nick = vo.getUser_nick();
	}
	
    // 사용자 아이디
	@Id
    private String user_id;

    // 사용자 비밀번호 
    private String user_pw;

    // 사용자 이름 
    private String user_name;

    // 사용자 이메일 
    private String user_email;

    // 사용자 닉네임 
    private String user_nick;

    // 사용자 사진 
    private String user_photo;

    // 사용자 구분 
    private String user_type;

    // 생성 일시
    @CreationTimestamp
    private Timestamp created_at;
    
}
