package edu.tongji.tjlms.repository;

import edu.tongji.tjlms.model.ClassEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Repository
public interface ClassRepository extends JpaRepository<ClassEntity,String> {
    @Transactional
    @Modifying
    @Query("UPDATE ClassEntity Set stuNum=stuNum+?2 where id=?1")
    void updateNum(String id,Integer num);

    @Transactional
    @Modifying
    @Query("UPDATE ClassEntity Set assistId=?2 where id=?1")
    void updateAssist(String classId, String TAId);

    List<ClassEntity> findAllByTeacherIdOrRespIdOrAssistId(String teacherId,String respId,String assistId);
    ClassEntity findAllById(String id);
}
