package edu.tongji.tjlms.controller;

import edu.tongji.tjlms.service.email.EmailSendServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@ResponseBody
public class VerificationCodeCheckController {
    @PostMapping("post/check/code")
    public ResponseEntity<?> checkCode(String code)
    {
        try
        {
            String verificationCode = EmailSendServiceImpl.getVerificationCode();
//            System.out.println(verificationCode);
            // compare the verification code
            if(!code.equals(verificationCode))
            {
                EmailSendServiceImpl.setVerificationCodeToEmpty();
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false);
            }
            EmailSendServiceImpl.setVerificationCodeToEmpty();
            return ResponseEntity.status(HttpStatus.OK).body(true);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("数据库请求错误");
        }

    }

}
