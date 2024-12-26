package com.tom.basic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tom.basic.entity.TbCreditcard;

@Repository
public interface CreditcardRepo extends JpaRepository<TbCreditcard, Long>{

}
