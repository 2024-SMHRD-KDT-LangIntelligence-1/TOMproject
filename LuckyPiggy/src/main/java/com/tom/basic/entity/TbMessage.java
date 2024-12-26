package com.tom.basic.entity;

import java.sql.Timestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TbMessage {

    // 메시지 식별자 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long msg_idx;

    // 메시지 내용 
    private String msg_content;

    // 등록 일자 
    private Timestamp created_at;
	
    
}
