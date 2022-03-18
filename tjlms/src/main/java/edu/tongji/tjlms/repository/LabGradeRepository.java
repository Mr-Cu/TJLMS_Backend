package edu.tongji.tjlms.repository;

import edu.tongji.tjlms.model.LabGradeEntity;
import edu.tongji.tjlms.model.LabGradeEntityPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("GradeRepository")
public interface LabGradeRepository extends JpaRepository<LabGradeEntity, LabGradeEntityPK>{
    @Transactional
    @Modifying
    @Query("UPDATE LabGradeEntity g SET g.visible=1, g.updateDate=?2 WHERE g.classId=?1")
    void release(String classId, String time);

    LabGradeEntity findByStuIdAndLabId(String stuId,Integer labId);
}
