package edu.tongji.tjlms.repository;

import edu.tongji.tjlms.model.TakesEntity;
import edu.tongji.tjlms.model.TakesEntityPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface TakesRepository extends JpaRepository<TakesEntity, TakesEntityPK> {
    @Modifying
    @Transactional
    void deleteByStuIdAndClassId(String id,String classId);

    List<TakesEntity> findAllByClassId(String classId);
    TakesEntity findByStuId(String id);
}
