package edu.tongji.tjlms.service.feedback;

import edu.tongji.tjlms.dto.FeedBackDto;
import edu.tongji.tjlms.dto.ReplyDto;
import edu.tongji.tjlms.model.FeedbackEntity;
import edu.tongji.tjlms.repository.FeedbackRepository;
import edu.tongji.tjlms.repository.StudentRepository;
import edu.tongji.tjlms.repository.TeacherRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class FeedbackServiceImpl implements FeedbackService{

    @Resource
    FeedbackRepository feedbackRepository;

    @Resource
    StudentRepository studentRepository;

    @Resource
    TeacherRepository teacherRepository;
    @Override
    public String feedback(FeedBackDto fbd) {
        FeedbackEntity feedback = new FeedbackEntity();
        feedback.setFeedbacker(fbd.getFeedbacker());
        feedback.setFbTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        feedback.setFbTitle(fbd.getTitle());
        feedback.setFbContent(fbd.getContent());
        feedback.setAnonymous(fbd.getIsAnonymous());
        feedback.setIsReplied(false);
        feedbackRepository.save(feedback);
        return "反馈提交成功";
    }

    @Override
    public String reply(ReplyDto rd) {
        String now = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        feedbackRepository.reply(rd.getReplier(),rd.getTitle(),rd.getContent(),now,rd.getId());

        return "回复成功";
    }

    @Override
    public Page<FeedbackEntity> myFeedback(String id, Integer pageNum, Integer pageSize) {
        Page<FeedbackEntity> page = feedbackRepository.findAllByFeedbacker(id, PageRequest.of(pageNum-1,pageSize));
        if(page.getContent().isEmpty())
        {
            return null;
        }
        return page;
    }

    @Override
    public Page<FeedbackEntity> myReply(String id, Integer pageNum, Integer pageSize) {
        Page<FeedbackEntity> page =  feedbackRepository.findAllByReplier(id, PageRequest.of(pageNum-1,pageSize));
        if(page.getContent().isEmpty()) {
            return null;
        }
        for(FeedbackEntity feedback:page.getContent()) {
            if(feedback.getAnonymous()) {
                feedback.setFeedbacker("匿名反馈");
            }
            else {
                feedback.setFeedbacker(feedback.getFeedbacker()+' '+studentRepository.findById(feedback.getFeedbacker()).get().getName());
            }
        }
        return page;
    }

    @Override
    public Page<FeedbackEntity> getAllFeedback(Integer pageNum, Integer pageSize) {
        Page<FeedbackEntity> page = feedbackRepository.findAll(PageRequest.of(pageNum-1,pageSize));
        if(page.getContent().isEmpty()) {
            return null;
        }
        for(FeedbackEntity feedback:page.getContent()) {
            if(feedback.getAnonymous()) {
                feedback.setFeedbacker("匿名反馈");
            }
            else {
                feedback.setFeedbacker(feedback.getFeedbacker()+' '+studentRepository.findById(feedback.getFeedbacker()).get().getName());
            }
        }
        return page;
    }

    @Override
    public Map<String, Object> myFeedbackWithName(String id, Integer pageNum, Integer pageSize) {
        Map<String,Object> map = new HashMap<>();
        Page<FeedbackEntity> page =  feedbackRepository.findAllByFeedbacker(id, PageRequest.of(pageNum-1,pageSize));
        if(page.getContent().isEmpty()) {
            return null;
        }
        List<String> names = new ArrayList<>();
        map.put("feedbacks",page.getContent());
        for(FeedbackEntity feedback:page.getContent()) {
            if(feedback.getReplier() == null) {
                names.add(null);
            }
            else {
                names.add(feedback.getReplier()+' '+teacherRepository.findById(feedback.getReplier()).get().getName());
            }
        }
        map.put("names",names);
        return map;
    }

    @Override
    public FeedbackEntity getFeedbackById(Integer id) {
        Optional<FeedbackEntity> feedback = feedbackRepository.findById(id);
        return feedback.orElse(null);
    }
}
