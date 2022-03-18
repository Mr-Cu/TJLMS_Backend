package edu.tongji.tjlms.service.feedback;

import edu.tongji.tjlms.dto.FeedBackDto;
import edu.tongji.tjlms.dto.ReplyDto;
import edu.tongji.tjlms.model.FeedbackEntity;
import org.springframework.data.domain.Page;

import java.util.Map;

public interface FeedbackService {
    String feedback(FeedBackDto fbd);
    String reply(ReplyDto rd);
    Page<FeedbackEntity> myFeedback(String id,Integer pageNum,Integer pageSize);
    Page<FeedbackEntity> myReply(String id,Integer pageNum,Integer pageSize);
    Page<FeedbackEntity> getAllFeedback(Integer pageNum, Integer pageSize);
    Map<String, Object> myFeedbackWithName(String id,Integer pageNum,Integer pageSize);
    FeedbackEntity getFeedbackById(Integer id);

}
