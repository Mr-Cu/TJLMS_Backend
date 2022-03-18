package edu.tongji.tjlms.service.user;

import edu.tongji.tjlms.dto.PermDto;
import edu.tongji.tjlms.model.StudentEntity;
import edu.tongji.tjlms.model.TeacherEntity;
import edu.tongji.tjlms.repository.StudentRepository;
import edu.tongji.tjlms.repository.TeacherRepository;
import edu.tongji.tjlms.util.ExcelResolverUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Resource
    StudentRepository studentRepository;
    @Resource
    TeacherRepository teacherRepository;

    @Override
    public List<StudentEntity> getAllStudents() {
        List<StudentEntity> ret = studentRepository.findAll();
        for(StudentEntity student:ret)
        {
            student.setPassword(null);
        }
        return ret;
    }

    @Override
    public List<TeacherEntity> getAllTeachers() {
        List<TeacherEntity> ret = teacherRepository.findAll();
        for(TeacherEntity teacher:ret)
        {
            teacher.setPassword(null);
        }
        return ret;
    }

    @Override
    public String insertStudent(String filePath) {
        List<StudentEntity> studentList = ExcelResolverUtil.resolveSheetStudent(filePath);
        if(studentList == null)
        {
            return "添加失败";
        }
        for(StudentEntity student:studentList)
        {
            student.setVerified(false);
        }
        studentRepository.saveAll(studentList);
        return "添加成功";
    }

    @Override
    public String insertTeacher(String filePath) {
        List<TeacherEntity> teacherList = ExcelResolverUtil.resolveSheetTeacher(filePath);
        if(teacherList == null)
        {
            return "添加失败";
        }
        for(TeacherEntity teacher:teacherList)
        {
            teacher.setVerified(false);
        }
        teacherRepository.saveAll(teacherList);
        return "添加成功";
    }

    @Override
    public String insertStudent(StudentEntity student) {
        studentRepository.save(student);
        return "添加成功";
    }

    @Override
    public String insertTeacher(TeacherEntity teacher) {
        teacherRepository.save(teacher);
        return "添加成功";
    }

    @Override
    public String deleteStudent(String id) {
        studentRepository.deleteById(id);
        return "删除成功";
    }

    @Override
    public String deleteTeacher(String id) {
        teacherRepository.deleteById(id);
        return "删除成功";
    }

    @Override
    public String modifyPerm(List<PermDto> list) {
        for(PermDto permission:list)
        {
            teacherRepository.modifyPerm(permission.getType(),
                                         permission.getGrade(),
                                         permission.getReleaseLab(),
                                         permission.getId());
        }
        return "权限批量修改成功";
    }

    @Override
    public String modifyPerm(PermDto permission) {
        teacherRepository.modifyPerm(permission.getType(),
                permission.getGrade(),
                permission.getReleaseLab(),
                permission.getId());
        return "权限修改成功";
    }

    @Override
    public Page<StudentEntity> getStudentsPaged( Integer pageNum, Integer pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNum-1,pageSize);
        Page<StudentEntity> page = studentRepository.findAll(pageRequest);
        for(StudentEntity student:page.getContent())
        {
            student.setPassword(null);
        }
        return page;
    }

    @Override
    public Page<TeacherEntity> getTeachersPaged(Integer pageNum, Integer pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNum-1,pageSize);
        Page<TeacherEntity> page = teacherRepository.findAll(pageRequest);
        for (TeacherEntity teacher:
             page.getContent()) {
            teacher.setPassword(null);
        }
        return page;
    }


}
