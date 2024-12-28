package com.tom.basic.entity;

import java.sql.Date;

import com.tom.basic.model.MoneybookVO;

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
public class TbMoneybook {
	
	public TbMoneybook(MoneybookVO vo) {
		userId = vo.getUser_id();
		paidAt = vo.getPaid_at();
		mbAmount = vo.getMb_amount();
		mbMethod = vo.getMb_method();
		methodNm = vo.getMethod_nm();
		mbMemo = vo.getMb_memo();
		mbIc = vo.getMb_ic();
	}
	
	
	
    // 사용 식별자 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long mb_idx;

    // 사용 구분(카테고리) 
    private String mbType;

    // 상점 명 
    private String shopNm;

    // 출금 금액 
    private String mbAmount;

    // 결제 수단 
    private String mbMethod;
    
    // 결제 수단 이름
    private String methodNm;

    // 카드(계좌) 번호 
    private String mbNum;

    // 결제 날짜 
    private Date paidAt;

    // 사용자 아이디 
    private String userId;

    // 사용 메모 
    private String mbMemo;
	
    // 입금 금액 
    private String mbIc;
    
}
