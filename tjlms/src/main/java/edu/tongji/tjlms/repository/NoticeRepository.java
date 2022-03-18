package edu.tongji.tjlms.repository;

import edu.tongji.tjlms.model.NoticeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoticeRepository extends JpaRepository<NoticeEntity,Integer> {
    List<NoticeEntity> findAllByReleaser(String releaser);
}
