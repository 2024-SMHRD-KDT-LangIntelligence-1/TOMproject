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
					" ge.category, SUM(ge.expenses) as sum " +
					"FROM graph_entity ge " +
					"GROUP BY category"
			, nativeQuery = true)
	List<postVO> findGroupBYReportWithNativeQuery();
}
