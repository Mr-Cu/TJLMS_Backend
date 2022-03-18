package edu.tongji.tjlms.repository;

import edu.tongji.tjlms.model.StuCheckEntity;
import edu.tongji.tjlms.model.StuCheckEntityPK;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StuCheckRepository extends JpaRepository<StuCheckEntity, StuCheckEntityPK> {
    List<StuCheckEntity> findAllByStuId(String stuId);
    StuCheckEntity findByStuIdAndCheckId(String stuId, Integer checkId);
    List<StuCheckEntity> findAllByCheckId(Integer checkId);
}
