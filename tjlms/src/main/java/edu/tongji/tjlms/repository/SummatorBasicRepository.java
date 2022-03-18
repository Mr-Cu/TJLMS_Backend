package edu.tongji.tjlms.repository;

import edu.tongji.tjlms.model.SummatorBasicEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface SummatorBasicRepository extends JpaRepository<SummatorBasicEntity,String> {
    @Transactional
    @Modifying
    @Query("UPDATE SummatorBasicEntity s SET s.mutable=false WHERE s.stuId=?1")
    void updateMutable(String stuId);

    @Transactional
    @Modifying
    @Query("UPDATE SummatorBasicEntity s SET s.isChecked=true WHERE s.stuId=?1")
    void updateCheck(String stuId);

    Optional<SummatorBasicEntity> findByStuId(String stuId);
}
