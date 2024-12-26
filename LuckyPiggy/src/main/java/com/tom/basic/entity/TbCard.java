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
public class TbCard {    
    
    // 카드 식별자 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long cardIdx;

    // 카드 이름 
    private String cardNm;

    // 금융기관 이름 
    private String bankNm;

    // 카드 설명 
    private String cardDesc;

    // 카드 이미지 
    private String cardImg;

    // 생성 일자 
    @CreationTimestamp
    private Timestamp createdAt;
    
}
