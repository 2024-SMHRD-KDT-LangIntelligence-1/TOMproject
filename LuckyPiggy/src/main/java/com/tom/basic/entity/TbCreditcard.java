package com.tom.basic.entity;

import java.sql.Date;
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
public class TbCreditcard {

	// 카드 식별자 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long cardIdx;

    // 카드 번호 
    private byte[] cardNum;

    // 카드 CVC 
    private byte[] cardCvc;

    // 암호화 벡터 
    private byte[] initVector;

    // 유효 기한 
    private Date expiredAt;

    // 카드 이름 
    private String cardNm;

    // 생성 일자 
    @CreationTimestamp
    private Timestamp createdAt;

    // 소유자 아이디 
    private String userId;

    // 카드 구분 
    private String cardType;
    
}
