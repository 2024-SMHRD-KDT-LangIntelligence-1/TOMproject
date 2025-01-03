package com.tom.basic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tom.basic.entity.TbCardsum;
import com.tom.basic.entity.TbMoneybook;
import com.tom.basic.model.CardsumVO;
import com.tom.basic.model.lineVO;
import com.tom.basic.model.postVO;

@Repository
public interface GraphRepo extends JpaRepository<TbMoneybook, Long>{
	@Query(value =
			"SELECT " +
					" mb.mb_type, SUM(REPLACE(mb.mb_amount,',','')) as sum " +
					"FROM tb_moneybook mb " +
					"Where mb.user_id = :userid " + 
					"GROUP BY mb_type " +
					"ORDER BY sum DESC"
			, nativeQuery = true)
	List<postVO> findGroupBYReportWithNativeQuery(@Param("userid") String userid);
	
	@Query(value =
			"select IFNULL(REPLACE(mb_amount,',',''),0) AS totalAmount, IFNULL(REPLACE(mb_ic,',',''),0) as totaIc, paid_at as paidAt " + 
			"from tb_moneybook " + 
			"where month(paid_at) = :month and user_id = :userid"
			, nativeQuery = true)
	List<lineVO> getlinetotal(String month, String userid);
}
