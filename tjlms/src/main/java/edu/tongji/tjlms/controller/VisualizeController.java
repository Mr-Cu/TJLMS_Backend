package edu.tongji.tjlms.controller;

import edu.tongji.tjlms.service.visualize.VisualizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
@ResponseBody
public class VisualizeController {
    @Autowired
    VisualizeService visualizeService;

    @GetMapping("/visualize/submission")
    public ResponseEntity<?> getSubmission(String classId,Integer labId)
    {
        try
        {
            Map<String,Integer> map = visualizeService.getSubmissionByClassAndLab(classId,labId);
            if(map == null)
            {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("暂无此班级在此实验中的提交作业可视化信息");
            }
            return ResponseEntity.status(HttpStatus.OK).body(map);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("数据库请求错误");
        }
    }

    @GetMapping("/visualize/grade/{labId}")
    public ResponseEntity<?> getGradeByLabId(@PathVariable("labId") Integer labId)
    {
        try
        {
            Map<String,Number> map = visualizeService.getAllGradeByLab(labId);
            if(map == null)
            {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("暂无此实验中的成绩可视化信息");
            }
            return ResponseEntity.status(HttpStatus.OK).body(map);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("数据库请求错误");
        }
    }

    @GetMapping("/visualize/grade/class")
    public ResponseEntity<?> getGradeByClassAndLab(String classId, Integer labId)
    {
        try
        {
            Map<String,Number> map = visualizeService.getGradeByClassAndLab(classId,labId);
            if(map == null)
            {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("暂无此班级在此实验中的成绩可视化信息");
            }
            return ResponseEntity.status(HttpStatus.OK).body(map);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("数据库请求错误");
        }
    }
}
