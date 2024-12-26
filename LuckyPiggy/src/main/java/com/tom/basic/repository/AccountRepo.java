package com.tom.basic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tom.basic.entity.TbAccount;

@Repository
public interface AccountRepo extends JpaRepository<TbAccount, Long>{

}
