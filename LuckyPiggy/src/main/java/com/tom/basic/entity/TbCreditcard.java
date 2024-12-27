package com.tom.basic.entity;

import java.sql.Date;
import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;

import com.tom.basic.model.CreditcardVO;

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

	public TbCreditcard(CreditcardVO vo) {
		userId = vo.getUser_id();
		cardNm = vo.getCard_nm();
		cardType = vo.getCard_type();
		cardCom = vo.getCard_com();
		cardNum = vo.getCard_num();
		benefitContent = vo.getBenefit_content();
		benefitStd = vo.getBenefit_std();
	}
	
	
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
    
    // 카드사
    private String cardCom;

    // 생성 일자 
    @CreationTimestamp
    private Timestamp createdAt;

    // 소유자 아이디 
    private String userId;

    // 카드 구분 
    private String cardType;
    
    // 카드 혜택 
    private String benefitContent;
    
    // 혜택 기준 
    private String benefitStd;
    
}
