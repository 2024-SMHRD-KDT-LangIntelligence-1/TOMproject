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
public class TbBudget {    
    
	// 예산 식별자 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long budgetIdx;

    // 기준 월 
    private Timestamp monthStd;

    // 예산 금액 
    private String budgetBalance;

    // 등록 일자 
    @CreationTimestamp
    private Timestamp createdAt;

    // 사용자 아이디 
    private String userId;    
    
}