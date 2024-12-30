package com.tom.basic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tom.basic.entity.TbMoneybook;

@Repository
public interface MoneybookRepo extends JpaRepository<TbMoneybook, Long>{
	
}
