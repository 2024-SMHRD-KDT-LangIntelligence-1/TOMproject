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
public class TbMoneybook {

    // 사용 식별자 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long mb_idx;

    // 사용 구분(카테고리) 
    private String mb_type;

    // 상점 명 
    private String shop_nm;

    // 사용 금액 
    private Integer mb_amount;

    // 결제 수단 
    private String mb_method;

    // 카드(계좌) 번호 
    private String mb_num;

    // 결제 날짜 
    private Timestamp paid_at;

    // 사용자 아이디 
    private String user_id;

    // 사용 메모 
    private String mb_memo;
	
    
}
