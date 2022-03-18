package edu.tongji.tjlms.controller;

import edu.tongji.tjlms.dto.FeedBackDto;
import edu.tongji.tjlms.dto.ReplyDto;
import edu.tongji.tjlms.model.FeedbackEntity;
import edu.tongji.tjlms.service.feedback.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/api")
@ResponseBody
public class FeedbackController {
    @Autowired
    FeedbackService feedbackService;

    @PostMapping("/post/feedback")
    ResponseEntity<String> feedback(@RequestBody FeedBackDto fbd)
    {
        try
        {
            return ResponseEntity.status(HttpStatus.OK).body(feedbackService.feedback(fbd));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("数据库请求错误");
        }
    }

    @PostMapping("/post/reply")
    ResponseEntity<String> reply(@RequestBody ReplyDto rd)
    {
        try
        {
            return ResponseEntity.status(HttpStatus.OK).body(feedbackService.reply(rd));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("数据库请求错误");
        }
    }

    @GetMapping("/get/my/feedback")
    ResponseEntity<?> getMyFeedback(String id,
                                    @RequestParam(defaultValue = "1") Integer pageNum,
                                    @RequestParam(defaultValue = "20") Integer pageSize)
    {
        try
        {
            Map<String,Object> map = feedbackService.myFeedbackWithName(id,pageNum,pageSize);
            if(map == null)
            {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("暂无发出的反馈信息");
            }
            return ResponseEntity.status(HttpStatus.OK).body(map);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("数据库请求错误");
        }
    }

    @GetMapping("/get/my/reply")
    ResponseEntity<?> getMyReply(String id,
                                 @RequestParam(defaultValue = "1") Integer pageNum,
                                 @RequestParam(defaultValue = "20") Integer pageSize)
    {
        try
        {
            Page<FeedbackEntity> page = feedbackService.myReply(id,pageNum,pageSize);
            if(page == null)
            {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("暂无发出的回复信息");
            }
            return ResponseEntity.status(HttpStatus.OK).body(page);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("数据库请求错误");
        }
    }

    @GetMapping("/get/all/feedback")
    ResponseEntity<?> getAllFeedback(@RequestParam(defaultValue = "1") Integer pageNum,
                                     @RequestParam(defaultValue = "20") Integer pageSize)
    {
        try
        {
            Page<FeedbackEntity> page = feedbackService.getAllFeedback(pageNum,pageSize);
            if(page == null)
            {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("暂无未回复的反馈信息");
            }
            return ResponseEntity.status(HttpStatus.OK).body(page);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("数据库请求错误");
        }
    }

    @GetMapping("/get/feedback/{feedbackId}")
    ResponseEntity<?> getFeedbackById(@PathVariable("feedbackId") Integer id)
    {
        try
        {
            FeedbackEntity feedback = feedbackService.getFeedbackById(id);
            if(feedback == null)
            {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("反馈不存在");
            }
            return ResponseEntity.status(HttpStatus.OK).body(feedback);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("数据库请求错误");
        }
    }

}
