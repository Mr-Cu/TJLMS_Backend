package edu.tongji.tjlms.repository;

import edu.tongji.tjlms.model.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface CourseRepository extends JpaRepository<CourseEntity,String> {
    @Transactional
    @Modifying
    @Query("UPDATE CourseEntity c SET c.ratio=?2 WHERE c.id=?1")
    void setRatio(String courseId,Double ratio);
}
