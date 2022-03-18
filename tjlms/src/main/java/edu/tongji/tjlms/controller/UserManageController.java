package edu.tongji.tjlms.controller;

import edu.tongji.tjlms.dto.PermDto;
import edu.tongji.tjlms.model.StudentEntity;
import edu.tongji.tjlms.model.TeacherEntity;
import edu.tongji.tjlms.service.user.UserService;
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
public class UserManageController {
    @Autowired
    UserService userService;

    Integer countStudent;
    Integer countTeacher;
    @GetMapping("/get/all/students")
    public ResponseEntity<?> getAllStudents()
    {
        try
        {
            List<StudentEntity> students = userService.getAllStudents();
            if(!students.isEmpty())
            {
                return ResponseEntity.status(HttpStatus.OK).body(students);
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("暂无学生信息");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("数据库请求错误");
        }
    }

    @GetMapping("/get/all/teachers")
    public ResponseEntity<?> getAllTeachers()
    {
        try
        {
            List<TeacherEntity> teachers = userService.getAllTeachers();
            if(!teachers.isEmpty())
            {
                return ResponseEntity.status(HttpStatus.OK).body(teachers);
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("暂无教师信息");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("数据库请求错误");
        }
    }

    @PostMapping("/post/students")
    public ResponseEntity<String> insertStudents(String filePath)
    {
        try
        {
            String ret = userService.insertStudent(filePath);
            if(ret.equals("添加失败"))
            {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ret);
            }
            else
            {
                return ResponseEntity.status(HttpStatus.OK).body(ret);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("数据库请求错误");
        }
    }

    @PostMapping("/post/teachers")
    public ResponseEntity<String> insertTeachers(@RequestBody String filePath)
    {
        try
        {
            String ret = userService.insertTeacher(filePath);
            if(ret.equals("添加失败"))
            {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ret);
            }
            else
            {
                return ResponseEntity.status(HttpStatus.OK).body(ret);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("数据库请求错误");
        }
    }

    @PostMapping("/admin/post/student")
    public ResponseEntity<String> insertStudent(@RequestBody StudentEntity student)
    {
        try
        {
            return ResponseEntity.status(HttpStatus.OK).body(userService.insertStudent(student));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("数据库请求错误");
        }
    }

    @PostMapping("/admin/post/teacher")
    public ResponseEntity<String> insertTeacher(@RequestBody TeacherEntity teacher)
    {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(userService.insertTeacher(teacher));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("数据库请求错误");
        }
    }

    @PostMapping("/delete/student/{studentId}")
    public ResponseEntity<String> deleteStudentById(@PathVariable("studentId") String id)
    {
        try
        {
            return ResponseEntity.status(HttpStatus.OK).body(userService.deleteStudent(id));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("删除失败");
        }
    }

    @PostMapping("/delete/teacher/{studentId}")
    public ResponseEntity<String> deleteTeacherById(@PathVariable("studentId") String id)
    {
        try
        {
            return ResponseEntity.status(HttpStatus.OK).body(userService.deleteTeacher(id));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("删除失败");
        }
    }

    @PostMapping("/post/modify/batch")
    public ResponseEntity<String> modifyPerm(@RequestBody List<PermDto> list)
    {
        try
        {
            return ResponseEntity.status(HttpStatus.OK).body(userService.modifyPerm(list));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("权限更改失败");
        }
    }
    @PostMapping("/post/modify")
    public ResponseEntity<String> modifyPerm(@RequestBody PermDto pd)
    {
        try
        {
            return ResponseEntity.status(HttpStatus.OK).body(userService.modifyPerm(pd));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("权限更改失败");
        }
    }

    @GetMapping("/get/students/page")
    public ResponseEntity<?> getStudentsPaged(@RequestParam(defaultValue = "1") Integer pageNum,
                                              @RequestParam(defaultValue = "20") Integer pageSize)
    {
        try
        {
            if(pageNum == null || pageNum == 0)
            {
                pageNum = 1;
            }
            if(countStudent != null && pageNum >= countStudent)
            {
                pageNum = countStudent;
            }
            Page<StudentEntity> page = userService.getStudentsPaged(pageNum,pageSize);
            if(page.isEmpty())
            {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("暂无学生信息");
            }
            countStudent = page.getTotalPages();
            Map<String,Object> map = new HashMap<>();
            map.put("data",page);
            map.put("pageNum",countStudent);
            return ResponseEntity.status(HttpStatus.OK).body(map);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("数据库请求错误");
        }
    }

    @GetMapping("/get/teachers/page")
    public ResponseEntity<?> getTeachersPaged(@RequestParam(defaultValue = "1") Integer pageNum,
                                              @RequestParam(defaultValue = "20") Integer pageSize)
    {
        try
        {
            if(pageNum == null || pageNum == 0)
            {
                pageNum = 1;
            }
            if(countTeacher != null && pageNum >= countTeacher)
            {
                pageNum = countTeacher;
            }
            Page<TeacherEntity> page = userService.getTeachersPaged(pageNum,pageSize);
            if (!page.isEmpty())
            {
                countTeacher = page.getTotalPages();
                Map<String, Object> map = new HashMap<>();
                map.put("data", page);
                map.put("pageNum", countTeacher);
                return ResponseEntity.status(HttpStatus.OK).body(map);
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("暂无教师信息");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("数据库请求错误");
        }
    }



}
