package com.tom.basic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tom.basic.entity.TbMoneybook;

@Repository
public interface MoneybookRepo extends JpaRepository<TbMoneybook, Long>{
	List<TbMoneybook> findAllByUserId(String user_id);
	
	@Query(value = "select distinct mb_type from tb_moneybook where user_id=:user_id order by mb_type asc", nativeQuery = true)
    List<String> findDistinctMbTypeByUserId(String user_id);
}
