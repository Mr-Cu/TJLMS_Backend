package edu.tongji.tjlms.repository;

import edu.tongji.tjlms.model.ReportFileEntity;
import edu.tongji.tjlms.model.ReportFileEntityPK;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReportFileRepository extends JpaRepository<ReportFileEntity, ReportFileEntityPK> {
    List<ReportFileEntity> findAllByStuId(String stuId);
    List<ReportFileEntity> findAllByLabId(Integer labId);
    ReportFileEntity findByStuIdAndLabId(String stuId,Integer labId);
}
