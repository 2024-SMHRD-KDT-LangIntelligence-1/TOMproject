package com.tom.basic.model;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// 추천 메시지
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageVO {

    // 메시지 식별자 
    private long msg_idx;

    // 메시지 내용 
    private String msg_content;

    // 등록 일자 
    private Timestamp created_at;
    
}
