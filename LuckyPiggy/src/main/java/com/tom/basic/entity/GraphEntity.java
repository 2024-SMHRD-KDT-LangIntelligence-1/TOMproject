package com.tom.basic.entity;

import com.tom.basic.model.GraphVO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GraphEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idx;
	private String category;
	private String expenses;
	
	public GraphEntity(GraphVO vo) {
		category = vo.getCategory();
		expenses = vo.getExpenses();
	}
	
}
