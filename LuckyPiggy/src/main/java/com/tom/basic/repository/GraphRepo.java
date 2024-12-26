package com.tom.basic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tom.basic.entity.GraphEntity;

@Repository
public interface GraphRepo extends JpaRepository<GraphEntity, Long>{
	GraphEntity findByCategory(String category);
}
