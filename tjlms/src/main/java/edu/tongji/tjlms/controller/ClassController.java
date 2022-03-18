package edu.tongji.tjlms.controller;

import edu.tongji.tjlms.dto.InsertClassDto;
import edu.tongji.tjlms.dto.InsertStudentDto;
import edu.tongji.tjlms.dto.InsertStudentsDto;
import edu.tongji.tjlms.model.ClassEntity;
import edu.tongji.tjlms.model.TakesEntity;
import edu.tongji.tjlms.model.TeacherEntity;
import edu.tongji.tjlms.service.clazz.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api")
@ResponseBody
public class ClassController {
    @Autowired
    ClassService classService;

    @GetMapping("/get/resp")
    public ResponseEntity<?> getResp()
    {
        try
        {
            List<TeacherEntity> ret = classService.getAllResp();
            if(ret != null)
            {
                return ResponseEntity.status(HttpStatus.OK).body(ret);
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("无责任教师信息");

        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("数据库请求错误");
        }
    }

    @GetMapping("/get/teacher")
    public ResponseEntity<?> getTeacher()
    {
        try
        {
            List<TeacherEntity> ret = classService.getAllTeacher();
            if(!ret.isEmpty())
            {
                return ResponseEntity.status(HttpStatus.OK).body(ret);
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("无普通教师信息");

        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("数据库请求错误");
        }
    }

    @GetMapping("/get/assist")
    public ResponseEntity<?> getAssist()
    {
        try
        {
            List<TeacherEntity> ret = classService.getAllAssist();
            if(!ret.isEmpty())
            {
                return ResponseEntity.status(HttpStatus.OK).body(ret);
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("无助教信息");

        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("数据库请求错误");
        }
    }

    @GetMapping("/get/classes")
    public ResponseEntity<?> getClasses()
    {
        try
        {
            List<ClassEntity> ret = classService.getAllClasses();
            if(!ret.isEmpty())
            {
                return ResponseEntity.status(HttpStatus.OK).body(ret);
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("无班级信息");

        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("数据库请求错误");
        }
    }

    @GetMapping("/get/students/{classId}")
    public ResponseEntity<?> getStudents(@PathVariable("classId") String classId)
    {
        try
        {
            List<TakesEntity> ret = classService.getAllStudentsByClassId(classId);
            if(!ret.isEmpty())
            {
                return ResponseEntity.status(HttpStatus.OK).body(ret);
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("此班级暂无学生");

        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("数据库请求错误");
        }
    }

    @GetMapping("/get/class/{classId}")
    public ResponseEntity<?> getClassById(@PathVariable("classId") String classId)
    {
        try
        {
            ClassEntity clazz = classService.getClassById(classId);
            if(clazz == null)
            {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("班级不存在");
            }
            return ResponseEntity.status(HttpStatus.OK).body(clazz);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("数据库请求错误");
        }
    }

    @PostMapping("/post/class")
    public ResponseEntity<String> insertClass(@RequestBody InsertClassDto icd)
    {
        try
        {
            return ResponseEntity.status(HttpStatus.OK).body(classService.insertClass(icd));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("数据库请求错误");
        }
    }

    @PostMapping("/delete/class/{classId}")
    public ResponseEntity<String> deleteClass(@PathVariable("classId") String id)
    {
        try
        {
            return ResponseEntity.status(HttpStatus.OK).body(classService.deleteClass(id));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("数据库请求错误");
        }
    }

    @PostMapping("/post/students/class")
    public ResponseEntity<String> insertStudents(@RequestBody InsertStudentsDto isd)
    {
        try
        {
            return ResponseEntity.status(HttpStatus.OK).body(classService.insertStudents(isd));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("数据库请求错误");
        }
    }
    @PostMapping("/post/student")
    public ResponseEntity<String> insertStudent(@RequestBody InsertStudentDto isd)
    {
        try
        {
            return ResponseEntity.status(HttpStatus.OK).body(classService.insertStudent(isd));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("数据库请求错误");
        }
    }

    @PostMapping("/delete/class/student")
    public ResponseEntity<String> deleteStudent( String studentId,String classId)
    {
        try
        {
            return ResponseEntity.status(HttpStatus.OK).body(classService.deleteStudent(studentId,classId));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("数据库请求错误");
        }
    }

    @PostMapping("/post/attendance/ratio")
    public ResponseEntity<String> setAttendanceRatio(Double attendanceRatio)
    {
        try
        {
            String ret = classService.setRatio(attendanceRatio);
            if(ret.equals("考勤比例超出合理范围"))
            {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ret);
            }
            return ResponseEntity.status(HttpStatus.OK).body(ret);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("数据库请求错误");
        }
    }

    @PostMapping("/post/set/assistant")
    public ResponseEntity<String> setAssistant(String classId, String assistId)
    {
        try {
            String ret = classService.setTA(classId,assistId);
            switch (ret)
            {
                case "助教信息不存在":
                {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ret);
                }
                case "该助教未激活":
                case "该教师不是助教":
                {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ret);
                }
            }
            return ResponseEntity.status(HttpStatus.OK).body(ret);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("数据库请求错误");
        }

    }
}
