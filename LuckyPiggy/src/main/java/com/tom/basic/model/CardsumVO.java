package com.tom.basic.model;

import java.math.BigInteger;
import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// 가계부
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardsumVO {
	
	// 결제 수단 이름
	private String method_nm;

	// 출금 금액
	private Double mb_amount;

	// 사용자 아이디
	private String benefit_std;

}
