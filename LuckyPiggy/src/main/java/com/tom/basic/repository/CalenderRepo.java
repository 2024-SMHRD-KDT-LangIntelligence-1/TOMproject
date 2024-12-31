package com.tom.basic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tom.basic.entity.TbMoneybook;

@Repository
public interface CalenderRepo extends JpaRepository<TbMoneybook, Long>{
	@Query(value = 
			"SELECT * " +
			"FROM tb_moneybook mb "+
			"WHERE MONTH(mb.paid_at) = ':month'"
			, nativeQuery = true)
	List<TbMoneybook> findEntriesInDecember2024(String month);
	
	
}
