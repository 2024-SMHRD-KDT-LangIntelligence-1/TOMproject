package com.tom.basic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tom.basic.entity.TbCardsum;
import com.tom.basic.entity.TbCreditcard;
import com.tom.basic.entity.TbMoneybook;
import com.tom.basic.model.CardsumVO;
import com.tom.basic.model.postVO;

@Repository
public interface CardsumRepo extends JpaRepository<TbMoneybook, Long>{
	@Query(value =
			"SELECT " +
					"mb.method_nm , sum(REPLACE(mb_amount,',','')) as sum , cd.benefit_std " +
					"FROM tb_moneybook mb Join tb_creditcard cd " +
					"on mb.method_nm = cd.card_nm " +
					"Where mb.user_id = :userid and mb.mb_type not in(\"교통비\", \"통신비\") and paid_at BETWEEN LAST_DAY(CURRENT_DATE() - INTERVAL 1 MONTH) + INTERVAL 1 DAY AND NOW()+5 " +
					"GROUP BY mb.method_nm, cd.benefit_std"
			, nativeQuery = true)
	List<CardsumVO> findGroupBYReportWithNativeQuery1(@Param("userid") String userid);
}



