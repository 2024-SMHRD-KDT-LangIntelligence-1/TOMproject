package com.tom.basic.model;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// 카드정보
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardVO {

    // 카드 식별자 
    private long card_idx;

    // 카드 이름 
    private String card_nm;

    // 금융기관 이름 
    private String bank_nm;

    // 카드 설명 
    private String card_desc;

    // 카드 이미지 
    private String card_img;

    // 생성 일자 
    private Timestamp created_at;
    
}
