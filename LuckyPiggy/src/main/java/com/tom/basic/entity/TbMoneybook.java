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
    private String mbType;

    // 상점 명 
    private String shopNm;

    // 사용 금액 
    private Integer mbAmount;

    // 결제 수단 
    private String mbMethod;

    // 카드(계좌) 번호 
    private String mbNum;

    // 결제 날짜 
    private Timestamp paidAt;

    // 사용자 아이디 
    private String userId;

    // 사용 메모 
    private String mbMemo;
	
    
}
