package edu.tongji.tjlms.service.preverify;

import edu.tongji.tjlms.dto.PreVerifyDto;
import edu.tongji.tjlms.dto.PreVerifyEmailDto;
import edu.tongji.tjlms.dto.PreVerifyExistsDto;
import edu.tongji.tjlms.model.StudentEntity;
import edu.tongji.tjlms.model.TeacherEntity;
import edu.tongji.tjlms.repository.StudentRepository;
import edu.tongji.tjlms.repository.TeacherRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

@Service
public class PreVerifyServiceImpl implements PreVerifyService{
    @Resource
    StudentRepository studentRepository;
    @Resource
    TeacherRepository teacherRepository;
    @Override
    public boolean exists(PreVerifyDto pvd) {
        int userType = pvd.getUserType();
        String id = pvd.getId();
        switch (userType)
        {
            case 1:
            {
                Optional<StudentEntity> student = studentRepository.findById(id);
                return student.filter(studentEntity -> !studentEntity.getVerified()).isPresent();
            }
            case 2:
            {
                Optional<TeacherEntity> teacher = teacherRepository.findById(id);
                return teacher.filter(teacherEntity -> !teacherEntity.getVerified()).isPresent();
            }
        }
        return false;
    }

    @Override
    public boolean exists(PreVerifyExistsDto pved) {
        int type = pved.getUserType();
        switch (type)
        {
            case 1:
            {
                Optional<StudentEntity> student = studentRepository.findById(pved.getId());
               if(student.isPresent())
               {
                   return student.get().getVerified();
               }
               return false;
            }
            case 2:
            {
                Optional<TeacherEntity> teacher = teacherRepository.findById(pved.getId());
                if(teacher.isPresent())
                {
                    return teacher.get().getVerified();
                }
                return false;
            }
        }
        return false;
    }

    @Override
    public boolean emailCorrect(PreVerifyEmailDto pved) {
        int type = pved.getUserType();
        switch (type)
        {
            case 1:
            {
                Optional<StudentEntity> student = studentRepository.findById(pved.getId());
                return student.get().getEmailAddr().equals(pved.getEmail());
            }
            case 2:
            {
                Optional<TeacherEntity> teacher = teacherRepository.findById(pved.getId());
                return teacher.get().getEmailAddr().equals(pved.getEmail());
            }
        }
        return false;
    }


}
