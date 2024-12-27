package com.tom.basic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tom.basic.entity.GraphEntity;
import com.tom.basic.model.postVO;

@Repository
public interface GraphRepo extends JpaRepository<GraphEntity, Long>{
	@Query(value =
			"SELECT " +
					" mb.mb_type, SUM(mb.mb_amount) as sum " +
					"FROM tb_moneybook mb " +
					"GROUP BY mb_type"
			, nativeQuery = true)
	List<postVO> findGroupBYReportWithNativeQuery();
}
