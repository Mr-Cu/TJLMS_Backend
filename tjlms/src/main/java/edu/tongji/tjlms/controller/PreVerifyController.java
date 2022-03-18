package edu.tongji.tjlms.controller;

import edu.tongji.tjlms.dto.PreVerifyDto;
import edu.tongji.tjlms.dto.PreVerifyEmailDto;
import edu.tongji.tjlms.dto.PreVerifyExistsDto;
import edu.tongji.tjlms.service.preverify.PreVerifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@ResponseBody
public class PreVerifyController {
    @Autowired
    private PreVerifyService preVerifyService;


    @PostMapping("/post/preVerify")
    public ResponseEntity<?> preVerify(@RequestBody PreVerifyDto pvd)
    {
        try
        {
            boolean existAndUnverified = preVerifyService.exists(pvd);
            if(existAndUnverified)
            {
                return ResponseEntity.status(HttpStatus.OK).body(existAndUnverified);
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(existAndUnverified);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("数据库请求错误");
        }

    }

    @PostMapping("/post/exists")
    public ResponseEntity<?> idExists(@RequestBody PreVerifyExistsDto pved)
    {
        try
        {
            boolean exists = preVerifyService.exists(pved);
            if(exists)
            {
                return ResponseEntity.status(HttpStatus.OK).body(exists);
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exists);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("数据库请求错误");
        }

    }

    @PostMapping("/post/checkEmail")
    public ResponseEntity<?> emailCorrect(@RequestBody PreVerifyEmailDto pved)
    {
        try
        {
            boolean correct = preVerifyService.emailCorrect(pved);
            if(correct)
            {
                return ResponseEntity.status(HttpStatus.OK).body(correct);
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(correct);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("数据库请求错误");
        }

    }

}
