package com.tom.basic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tom.basic.entity.TbCard;

@Repository
public interface CardRepo extends JpaRepository<TbCard, Long>{

}
