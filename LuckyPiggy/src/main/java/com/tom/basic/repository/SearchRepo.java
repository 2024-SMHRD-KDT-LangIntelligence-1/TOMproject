package com.tom.basic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tom.basic.entity.TbMoneybook;

@Repository
public interface SearchRepo extends JpaRepository<TbMoneybook, Long> {
	@Query(value = "SELECT * " + "FROM tb_moneybook mb "
			+ "WHERE (mb.mb_type LIKE %:keyword% OR mb.shop_nm LIKE %:keyword%) "
			+ "AND mb.user_id = :userid", nativeQuery = true)
	List<TbMoneybook> searchquery(@Param("keyword") String keyword, @Param("userid") String userid);
}
