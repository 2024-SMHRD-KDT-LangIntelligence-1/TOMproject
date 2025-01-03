package com.tom.basic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tom.basic.entity.TbBudget;
import com.tom.basic.entity.TbMoneybook;
import com.tom.basic.model.BudgetVO;

@Repository
public interface BudgetRepo extends JpaRepository<TbBudget, Long>{
	TbBudget findByUserId(String userid);
}

