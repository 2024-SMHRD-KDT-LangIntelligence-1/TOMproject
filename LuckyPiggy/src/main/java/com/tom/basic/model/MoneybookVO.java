package com.tom.basic.model;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// 가계부
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MoneybookVO {

	// 사용 식별자
	private long mb_idx;

	// 사용 구분(카테고리)
	private String mb_type;

	// 상점 명
	private String shop_nm;

	// 출금 금액
	private String mb_amount;

	// 결제 수단
	private String mb_method;

	// 결제 수단 이름
	private String method_nm;

	// 카드(계좌) 번호
	private String mb_num;

	// 결제 날짜
	private Date paid_at;

	// 사용자 아이디
	private String user_id;

	// 사용 메모
	private String mb_memo;

	// 입금 금액
	private String mb_ic;

}
