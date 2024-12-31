package com.tom.basic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tom.basic.entity.TbMoneybook;

public interface CalenderRepo extends JpaRepository<TbMoneybook, Long>{
	@Query(value = 
			"SELECT * " +
			"FROM tb_moneybook mb "+
			"WHERE MONTH(m.paidAt) = month"
			, nativeQuery = true)
	List<TbMoneybook> findEntriesInDecember2024(@Param("month") String month);

}
