package com.tom.basic.entity;

import com.tom.basic.model.GraphVO;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GraphEntity {

	private int posSeq;
	private String posType;
	private String posTime;
	private int posCount;
	@Id
	private String mbId; // Repo 제네릭스에서 Id타입을 써줘야 함
	
	public GraphEntity(GraphVO vo) {
		posCount = vo.getPos_seq();
		posType = vo.getPos_type();
		posTime = vo.getPos_time();
		posCount = vo.getPos_count();
		mbId = vo.getMb_id();
	}
}
