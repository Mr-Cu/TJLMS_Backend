package edu.tongji.tjlms.repository;

import edu.tongji.tjlms.model.SummatorResultEntity;
import edu.tongji.tjlms.model.SummatorResultEntityPK;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SummatorResultRepository extends JpaRepository<SummatorResultEntity, SummatorResultEntityPK> {
    List<SummatorResultEntity> findAllByStuId(String id);

}
