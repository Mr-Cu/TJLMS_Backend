package edu.tongji.tjlms.repository;


import edu.tongji.tjlms.model.TeacherEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository("TeacherRepository")
public interface TeacherRepository extends JpaRepository<TeacherEntity,String> {
    Optional<TeacherEntity> findByEmailAddrAndPassword(String email, String password);

    @Transactional
    @Modifying
    @Query("UPDATE TeacherEntity t SET t.emailAddr=?1,t.password=?2,t.verified=1 WHERE t.id=?3")
    int verify( String email, String password, String id);

    @Transactional
    @Modifying
    @Query("UPDATE TeacherEntity t SET t.type=?1,t.grade=?2,t.releaseLab=?3 WHERE t.id=?4")
    void modifyPerm(Integer type,Boolean grade,Boolean releaseLab,String id);

    @Transactional
    @Modifying
    @Query("UPDATE TeacherEntity a SET a.password=?2 WHERE a.id=?2")
    void updatePwd(String id, String pwd);


    List<TeacherEntity> findAllByTypeAndVerified(Integer type,Boolean verified);


}
