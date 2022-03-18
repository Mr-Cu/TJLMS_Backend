package edu.tongji.tjlms.repository;

import edu.tongji.tjlms.model.AllGradeEntity;
import edu.tongji.tjlms.model.QueryGradePK;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AllGradeRepository extends JpaRepository<AllGradeEntity, QueryGradePK> {
    List<AllGradeEntity> findAllByLabId(Integer labId);
    List<AllGradeEntity> findAllByClassIdAndLabId(String classId,Integer labId);
}
