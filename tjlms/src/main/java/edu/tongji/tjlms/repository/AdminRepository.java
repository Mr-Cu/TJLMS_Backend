package edu.tongji.tjlms.repository;

import edu.tongji.tjlms.model.AdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository("AdminRepository")
public interface AdminRepository extends JpaRepository<AdminEntity, String> {
    Optional<AdminEntity> findByEmailAddrAndPassword(String emailAddr, String password);

    @Transactional
    @Modifying
    @Query("UPDATE AdminEntity a SET a.password=?2 WHERE a.id=?2")
    void updatePwd(String id, String pwd);

    @Transactional
    @Modifying
    @Query("UPDATE AdminEntity a SET a.password=?2, a.pwdReset=1 WHERE a.id=?1")
    void safePwd(String id,String pwd);
}
