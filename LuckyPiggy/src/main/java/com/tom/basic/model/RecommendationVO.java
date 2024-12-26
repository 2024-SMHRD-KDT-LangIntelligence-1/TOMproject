package com.tom.basic.model;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// 카드 추천
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecommendationVO {

    // 추천 식별자 
    private long reco_idx;

    // 사용자 아이디 
    private String user_id;

    // 카드 식별자 
    private long card_idx;

    // 추천 날짜 
    private Timestamp recommended_at;
    
}
