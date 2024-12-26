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
public class TbBenefit {    
    
	// 혜택 식별자 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long benefitIdx;

    // 카드 식별자 
    private long cardIdx;

    // 카드 혜택 
    private String benefitContent;

    // 혜택 기준 
    private String benefitStd;

    // 등록 일자 
    @CreationTimestamp
    private Timestamp createdAt;
    
}
