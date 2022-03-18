package edu.tongji.tjlms.repository;

import edu.tongji.tjlms.model.FeedbackEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface FeedbackRepository extends JpaRepository<FeedbackEntity,Integer> {
    @Transactional
    @Modifying
    @Query("UPDATE FeedbackEntity f set f.replier=?1,f.rpTitle=?2,f.rpContent=?3,f.rpTime=?4,f.isReplied=true WHERE f.id=?5")
    void reply(String replier,String rpTitie,String rpContent,String rpTime,Integer id);

    Page<FeedbackEntity> findAllByFeedbacker(String id, Pageable pageable);
    Page<FeedbackEntity> findAllByReplier(String id,Pageable pageable);
    Page<FeedbackEntity> findAll(Pageable pageable);
}
