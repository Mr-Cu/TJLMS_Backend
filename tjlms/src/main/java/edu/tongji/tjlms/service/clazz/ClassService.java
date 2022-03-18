package edu.tongji.tjlms.service.clazz;

import edu.tongji.tjlms.dto.InsertClassDto;
import edu.tongji.tjlms.dto.InsertStudentDto;
import edu.tongji.tjlms.dto.InsertStudentsDto;
import edu.tongji.tjlms.model.ClassEntity;
import edu.tongji.tjlms.model.TakesEntity;
import edu.tongji.tjlms.model.TeacherEntity;

import java.util.List;

public interface ClassService {
    String insertClass(InsertClassDto icd);
    String deleteClass(String id);
    List<ClassEntity> getAllClasses();
    ClassEntity getClassById(String id);

    String insertStudents(InsertStudentsDto isd);
    String insertStudent(InsertStudentDto isd);
    String deleteStudent(String id, String classId);
    List<TakesEntity> getAllStudentsByClassId(String id);

    List<TeacherEntity> getAllResp();
    List<TeacherEntity> getAllTeacher();
    List<TeacherEntity> getAllAssist();

    String setRatio(Double ratio);

    String setTA(String classId,String TAId);

}
