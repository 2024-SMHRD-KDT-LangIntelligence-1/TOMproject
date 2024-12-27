package com.tom.basic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tom.basic.entity.TbCard;
import com.tom.basic.entity.TbCreditcard;

@Repository
public interface CardRepo extends JpaRepository<TbCard, Long>{
}
