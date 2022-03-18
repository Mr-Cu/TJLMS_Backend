package edu.tongji.tjlms.controller;

import edu.tongji.tjlms.dto.GradeDto;
import edu.tongji.tjlms.model.*;
import edu.tongji.tjlms.service.grade.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@ResponseBody
public class GradeController {
    @Autowired
    GradeService gradeService;

    Integer countList;
    @GetMapping("/get/report/list/{teacherId}")
    public ResponseEntity<?> getReportList(@PathVariable("teacherId") String teacherId)
    {
        try
        {
            List<ReportListEntity> list = gradeService.getReportList(teacherId);
            if(list != null)
            {
                return ResponseEntity.status(HttpStatus.OK).body(list);
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("暂无实验报告信息");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("数据库请求错误");
        }
    }
    @GetMapping("/get/report/page/{teacherId}")
    public ResponseEntity<?> getReportPage(@PathVariable("teacherId") String teacherId, Integer pageNum,Integer pageSize)
    {
        try
        {
            if(pageNum == null || pageNum == 0)
            {
                pageNum = 1;
            }
            if(countList != null && pageSize >= countList)
            {
                pageNum = countList;
            }
            Page<ReportListEntity> page = gradeService.getReportListPaged(teacherId,pageNum,pageSize);
            if(page.isEmpty())
            {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("暂无实验报告信息");
            }
            countList = page.getTotalPages();
            Map<String,Object> map = new HashMap<>();
            map.put("data",page);
            map.put("pageNum",countList);
            return ResponseEntity.status(HttpStatus.OK).body(map);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("数据库请求错误");
        }
    }

    @PostMapping("/get/report/detail")
    public ResponseEntity<?> getReportDetail(@RequestBody ReportEntityPK pk)
    {
        try
        {
            Map<String,Object> report = gradeService.getReport(pk);
            if(report != null)
            {
                return ResponseEntity.status(HttpStatus.OK).body(report);
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("暂无此学生报告信息");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("数据库请求错误");
        }
    }

    @GetMapping("/get/my/classes")
    public ResponseEntity<?> getMyClasses(String teacherId)
    {
        try
        {
            List<ClassEntity> classes = gradeService.getMyClasses(teacherId);
            if(!classes.isEmpty())
            {
                return ResponseEntity.status(HttpStatus.OK).body(classes);
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("暂无您负责的班级");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("数据库请求错误");
        }
    }

    @GetMapping("/get/grade/individual")
    public ResponseEntity<?> getIndividualGrade(String stuId,Integer labId)
    {
        try
        {
            LabGradeEntity grade = gradeService.getParticularGrade(stuId, labId);
            if(grade == null)
            {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("暂无该学生成绩信息");
            }
            return ResponseEntity.status(HttpStatus.OK).body(grade);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("数据库请求错误");
        }

    }

    @PostMapping("/post/save/grade")
    public ResponseEntity<String> saveGrade(@RequestBody GradeDto grade)
    {
        try
        {
            return ResponseEntity.status(HttpStatus.OK).body(gradeService.save(grade));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("数据库请求错误");
        }
    }

    @PostMapping("/post/release/grade")
    public ResponseEntity<String> releaseGrade(String classId)
    {
        try
        {
            return ResponseEntity.status(HttpStatus.OK).body(gradeService.release(classId));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("数据库请求错误");
        }
    }

    @PostMapping("/post/release/individual")
    public ResponseEntity<String> releaseIndividual(@RequestBody GradeDto info)
    {
        try
        {
            return ResponseEntity.status(HttpStatus.OK).body(gradeService.release(info));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("数据库请求错误");
        }
    }

    @GetMapping("/get/summator/list")
    public ResponseEntity<?> getSummatorList(String teacherId,
                                             @RequestParam(defaultValue = "1") Integer pageNum,
                                             @RequestParam(defaultValue = "20") Integer pageSize)
    {
        try
        {
            Page<SummatorListEntity> page = gradeService.getSummatorListPaged(teacherId,pageNum,pageSize);
            if(page.getContent().isEmpty())
            {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("暂无加法器实验报告信息");
            }
            return ResponseEntity.status(HttpStatus.OK).body(page);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("数据库请求错误");
        }
    }

    @GetMapping("get/summator/detail")
    public ResponseEntity<?> getSummatorDetail(String stuId)
    {
        try
        {
            Map<String,Object> map = gradeService.getSummator(stuId);
            return ResponseEntity.status(HttpStatus.OK).body(map);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("数据库请求错误");
        }

    }


    @GetMapping("/get/lab/report")
    public ResponseEntity<?> getReportListByTeacherIdAndLabId(String teacherId,Integer labId)
    {
        try
        {
            List<ReportListEntity> list = gradeService.getReportByTeacherIdAndLabId(teacherId,labId);
            if(list.isEmpty())
            {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("暂无该实验相关实验报告");
            }
            return ResponseEntity.status(HttpStatus.OK).body(list);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("数据库请求错误");
        }
    }

}
