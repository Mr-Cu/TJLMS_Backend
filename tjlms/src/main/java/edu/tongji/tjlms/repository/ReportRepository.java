package edu.tongji.tjlms.repository;

import edu.tongji.tjlms.model.ReportEntity;
import edu.tongji.tjlms.model.ReportEntityPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface ReportRepository extends JpaRepository<ReportEntity, ReportEntityPK> {
    ReportEntity findByStuIdAndLabId(String stuId,Integer labId);
    Long countByStuId(String id);

    @Transactional
    @Modifying
    @Query("UPDATE ReportEntity r SET r.isChecked=true WHERE r.stuId=?1 AND r.labId=?2")
    void updateChecked(String stuId,Integer labId);
}
