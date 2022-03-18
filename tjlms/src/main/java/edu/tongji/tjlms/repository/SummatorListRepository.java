package edu.tongji.tjlms.repository;

import edu.tongji.tjlms.model.SummatorListEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SummatorListRepository extends JpaRepository<SummatorListEntity,String> {
    Page<SummatorListEntity> findAllByTeacherId(String teacherId, Pageable pageable);

}
