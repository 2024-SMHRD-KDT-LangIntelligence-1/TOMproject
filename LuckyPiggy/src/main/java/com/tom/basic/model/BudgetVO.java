package com.tom.basic.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// 월별 예산
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BudgetVO {

	// 예산 식별자
	private long budget_idx;

	// 기준 월
	private LocalDateTime month_std;

	// 예산 금액
	private String budget_balance;

	// 등록 일자
	private LocalDateTime created_at;

	// 사용자 아이디
	private String user_id;

}
