package edu.tongji.tjlms.repository;

import edu.tongji.tjlms.model.MaterialEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MaterialRepository extends JpaRepository<MaterialEntity,Integer> {
    List<MaterialEntity> findAllByLabId(Integer labId);
    Page<MaterialEntity> findAllByLabId(Integer labId,Pageable pageable);
    Page<MaterialEntity> findAllByUploader(String uploader, Pageable pageable);
}
