package edu.tongji.tjlms.repository;

import edu.tongji.tjlms.model.QueryGradeEntity;
import edu.tongji.tjlms.model.QueryGradePK;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QueryGradeRepository extends JpaRepository<QueryGradeEntity, QueryGradePK> {
    List<QueryGradeEntity> findAllByStuId(String stuId);
    QueryGradeEntity findByStuIdAndLabId(String stuId,Integer labId);
}
