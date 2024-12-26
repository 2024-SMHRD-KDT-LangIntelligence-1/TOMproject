package com.tom.basic.model;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// 카드 혜택
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BenefitVO {

    // 혜택 식별자 
    private long benefit_idx;

    // 카드 식별자 
    private long card_idx;

    // 카드 혜택 
    private String benefit_content;

    // 혜택 기준 
    private String benefit_std;

    // 등록 일자 
    private Timestamp created_at;
    
}
