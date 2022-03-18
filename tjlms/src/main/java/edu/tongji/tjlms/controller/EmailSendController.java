package edu.tongji.tjlms.controller;

import edu.tongji.tjlms.dto.EmailDto;
import edu.tongji.tjlms.service.email.EmailSendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Charles Gao
 * @date 2021/10/20
 * @description the email sending controller
 */
@RestController
@RequestMapping("/api")
@ResponseBody
public class EmailSendController {
    @Autowired
    private EmailSendService emailSendService;

    /**
     * send email
     * @param ed the data for sending the email
     * @return the response code with information
     */
    @PostMapping("/post/verify")
    public ResponseEntity<String> sendEmail(@RequestBody EmailDto ed)
    {
        try
        {
           String ret = emailSendService.sendEmail(ed);
           if(ret.equals("发送成功"))
           {
               return ResponseEntity.status(HttpStatus.OK).body(ret);
           }
           else if(ret.equals("学工号错误"))
           {
               return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ret);
           }
           else
           {
               return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ret);
           }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("发送失败");
        }
    }
}
