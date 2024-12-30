package com.tom.basic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tom.basic.entity.TbMoneybook;

@Repository
public interface SearchRepo extends JpaRepository<TbMoneybook, Long>{
	List<TbMoneybook> findByMbTypeContaining(String userid);
}
