package com.tom.basic.entity;

import java.math.BigInteger;
import java.sql.Date;

import com.tom.basic.model.CardsumVO;
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
public class TbCardsum {

	public TbCardsum(CardsumVO vo) {
		//userId = vo.getUser_id();
		mb_amount = vo.getMb_amount();
		methodNm = vo.getMethod_nm();
		benefitStd = vo.getBenefit_std();
		//cardNm = vo.getCard_nm();		
	}

	// 사용 식별자
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//private long mb_idx;

	// 출금 금액
	private Double mb_amount;

	// 결제 수단 이름
	private String methodNm;

	// 사용자 아이디
	//private String userId;

	// 사용자 아이디
	private String benefitStd;
	
	// 사용자 아이디
	//private String cardNm;
	

}
