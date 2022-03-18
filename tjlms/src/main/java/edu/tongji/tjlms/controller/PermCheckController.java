package edu.tongji.tjlms.controller;

import edu.tongji.tjlms.service.perm.PermService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@ResponseBody
public class PermCheckController {
    @Autowired
    PermService permService;

    @GetMapping("/check/grade/{teacherId}")
    public ResponseEntity<?> checkGrade(@PathVariable("teacherId") String teacherId)
    {
        try
        {
            return permService.canGrade(teacherId) ? ResponseEntity.status(HttpStatus.OK).body(true):
                    ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false);
        }
        catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("数据库请求错误");
        }

    }

    @GetMapping("/check/release/{teacherId}")
    public ResponseEntity<?> checkRelease(@PathVariable("teacherId") String teacherId)
    {
        try
        {
            return permService.canRelease(teacherId) ? ResponseEntity.status(HttpStatus.OK).body(true) :
                    ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false);
        }
        catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("数据库请求错误");
        }
    }

    @GetMapping("/check/ddl/{teacherId}")
    public ResponseEntity<?> checkDdl(@PathVariable("teacherId") Integer labId)
    {
        try
        {
            return permService.earlierThanDdl(labId) ? ResponseEntity.status(HttpStatus.OK).body(true) :
                    ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false);
        }
        catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("数据库请求错误");
        }

    }

    @GetMapping("/check/notice/{teacherId}")
    public ResponseEntity<?> checkNotice(@PathVariable("teacherId") String teacherId)
    {
        try
        {
            return permService.isResp(teacherId) ? ResponseEntity.status(HttpStatus.OK).body(true) :
                    ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false);
        }
        catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("数据库请求错误");
        }
    }


}
