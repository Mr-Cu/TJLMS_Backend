package edu.tongji.tjlms.repository;

import edu.tongji.tjlms.model.LabEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LabRepository extends JpaRepository<LabEntity,Integer> {
    LabEntity findAllById(Integer id);
}
