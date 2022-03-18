package edu.tongji.tjlms.repository;

import edu.tongji.tjlms.model.QueryReportEntity;
import edu.tongji.tjlms.model.QueryReportPK;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QueryReportRepository extends JpaRepository<QueryReportEntity, QueryReportPK> {
    List<QueryReportEntity> findAllByStuId(String stuId);

    List<QueryReportEntity> findByStuIdAndLabId(String stuId,Integer labId);

}
