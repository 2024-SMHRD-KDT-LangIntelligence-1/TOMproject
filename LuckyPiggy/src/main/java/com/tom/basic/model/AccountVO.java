package com.tom.basic.model;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// 계좌
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountVO {

    // 계좌 식별자 
    private long acc_idx;

    // 금융기관명 
    private String bank_nm;

    // 계좌번호 
    private String acc_num;

    // 잔액 
    private Long balance;

    // 등록 일자 
    private Timestamp created_at;

    // 사용자 아이디 
    private String user_id;
    
}
