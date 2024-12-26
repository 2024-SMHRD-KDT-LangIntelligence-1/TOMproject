package com.tom.basic.model;

import java.sql.Date;
import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// 신용카드
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreditcardVO {

    // 카드 식별자 
    private long card_idx;

    // 카드 번호 
    private byte[] card_num;

    // 카드 CVC 
    private byte[] card_cvc;

    // 암호화 벡터 
    private byte[] init_vector;

    // 유효 기한 
    private Date expired_at;

    // 카드 이름 
    private String card_nm;

    // 생성 일자 
    private Timestamp created_at;

    // 소유자 아이디 
    private String user_id;

    // 카드 구분 
    private String card_type;
    
}
