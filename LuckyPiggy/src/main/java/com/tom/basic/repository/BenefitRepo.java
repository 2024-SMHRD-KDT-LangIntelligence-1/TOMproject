package com.tom.basic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tom.basic.entity.TbBenefit;

@Repository
public interface BenefitRepo extends JpaRepository<TbBenefit, Long>{

}
