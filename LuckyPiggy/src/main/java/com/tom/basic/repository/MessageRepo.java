package com.tom.basic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tom.basic.entity.TbMessage;

@Repository
public interface MessageRepo extends JpaRepository<TbMessage, Long>{

}
