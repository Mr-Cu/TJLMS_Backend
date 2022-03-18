package edu.tongji.tjlms.repository;

import edu.tongji.tjlms.model.CheckEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CheckRepository extends JpaRepository<CheckEntity,Integer> {
    List<CheckEntity> findAllByClassId(String classId);
}
