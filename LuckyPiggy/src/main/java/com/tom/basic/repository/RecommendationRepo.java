package com.tom.basic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tom.basic.entity.TbRecommendation;

@Repository
public interface RecommendationRepo extends JpaRepository<TbRecommendation, Long>{

}
