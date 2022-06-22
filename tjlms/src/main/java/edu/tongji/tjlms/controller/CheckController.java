package edu.tongji.tjlms.controller;

import edu.tongji.tjlms.dto.StuGetCheckDto;
import edu.tongji.tjlms.dto.PostCheckDto;
import edu.tongji.tjlms.dto.TeacherGetCheckDto;
import edu.tongji.tjlms.service.check.CheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@ResponseBody
@RequestMapping("/api")
public class CheckController {

    @Autowired
    CheckService checkService;

    @PostMapping("post/check")
    public ResponseEntity<String> postCheck(@RequestBody PostCheckDto pcd)
    {
        try
        {
            String checkState=checkService.postCheck(pcd);
            if(checkState == "班级编号不能为空"){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("班级编号不能为空");
            }
            if(checkState == "不存在该班级"){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("不存在该班级");
            }
            return ResponseEntity.status(HttpStatus.OK).body(checkState);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("数据库请求错误");
        }
    }

    @GetMapping("/stu/get/check")
    public ResponseEntity<?> getCheckStu(String stuId)
    {
        try
        {
            List<StuGetCheckDto> checks = checkService.getAllCheckByStuId(stuId);
            if(checks == null){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("暂无签到信息");
            }
            return ResponseEntity.status(HttpStatus.OK).body(checks);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("数据库请求错误");
        }

    }

    @GetMapping("/teacher/get/check")
    public ResponseEntity<?> getCheckTeacher(String classId)
    {
        try
        {
            List<TeacherGetCheckDto> checks = checkService.getAllCheckByClassId(classId);
            if(checks == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("暂无签到信息");
            }
            return ResponseEntity.status(HttpStatus.OK).body(checks);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("数据库请求错误");
        }
    }

    @PostMapping("/check/in")
    public ResponseEntity<String> checkIn(String stuId,Integer checkId)
    {
        try
        {
            String ret = checkService.submitCheck(stuId,checkId);
            if(ret.equals("学生编号不能为空")||ret.equals("签到编号不能为空")||ret.equals("时间转换失败")) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ret);
            }
            else if(ret.equals("未到签到时间，无法签到")||ret.equals("签到已结束，无法签到")) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ret);
            }
            else if(ret.equals("签到成功")) {
                return ResponseEntity.status(HttpStatus.OK).body(ret);
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ret);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("数据库请求错误");
        }
    }
}
