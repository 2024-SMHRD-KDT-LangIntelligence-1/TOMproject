package com.tom.basic.model;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// 사용자
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVO {

    // 사용자 아이디 
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
    private Timestamp created_at;
    
}
