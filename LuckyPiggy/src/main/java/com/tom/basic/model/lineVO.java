package com.tom.basic.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class lineVO {
	private String totalAmount;
	private String totalIc;
	private Date paidAt;

}
