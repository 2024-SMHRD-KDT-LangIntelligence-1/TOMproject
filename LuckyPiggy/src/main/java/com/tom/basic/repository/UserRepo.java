package com.tom.basic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tom.basic.entity.TbUser;

@Repository
public interface UserRepo extends JpaRepository<TbUser, String>{
	TbUser findByUserIdAndUserPw(String user_id, String user_pw);
}
