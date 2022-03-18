package edu.tongji.tjlms.service.user;

import edu.tongji.tjlms.dto.PermDto;
import edu.tongji.tjlms.model.StudentEntity;
import edu.tongji.tjlms.model.TeacherEntity;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {
    List<StudentEntity> getAllStudents();
    List<TeacherEntity> getAllTeachers();

    String insertStudent(String filePath);
    String insertTeacher(String filePath);

    String insertStudent(StudentEntity student);
    String insertTeacher(TeacherEntity teacher);

    String deleteStudent(String id);
    String deleteTeacher(String id);

    String modifyPerm(List<PermDto> list);
    String modifyPerm(PermDto perm);
    Page<StudentEntity> getStudentsPaged(Integer pageNum,Integer pageSize);
    Page<TeacherEntity> getTeachersPaged(Integer pageNum, Integer pageSize);
}
