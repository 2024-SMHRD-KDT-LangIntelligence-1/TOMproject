package com.tom.basic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tom.basic.entity.TbBudget;

@Repository
public interface BudgetRepo extends JpaRepository<TbBudget, Long>{
	TbBudget findByUserId(String user_id);
	
	
}

