package com.tom.basic.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GraphVO {
	
	private int pos_seq;
	private String pos_type;
	private String pos_time;
	private int pos_count;
	private String mb_id;
}
