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
public class TbRecommendation {

    // 추천 식별자 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long recoIdx;

    // 사용자 아이디 
    private String userId;

    // 카드 식별자 
    private long cardIdx;

    // 추천 날짜 
    @CreationTimestamp
    private Timestamp recommendedAt;
    
}
