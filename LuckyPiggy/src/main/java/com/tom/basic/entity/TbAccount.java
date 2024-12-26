package com.tom.basic.entity;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;

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
public class TbAccount {    
    
	// 계좌 식별자 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long accIdx;

    // 금융기관명 
    private String bankNm;

    // 계좌번호 
    private String accNum;

    // 잔액 
    private Long balance;

    // 등록 일자 
    @CreationTimestamp
    private Timestamp createdAt;

    // 사용자 아이디 
    private String userId;
    
    
}
