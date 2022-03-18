package edu.tongji.tjlms.service.perm;

import edu.tongji.tjlms.model.LabEntity;
import edu.tongji.tjlms.model.TeacherEntity;
import edu.tongji.tjlms.repository.LabRepository;
import edu.tongji.tjlms.repository.TeacherRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class PermServiceImpl implements PermService {

    @Resource
    TeacherRepository teacherRepository;
    @Resource
    LabRepository labRepository;
    @Override
    public boolean canGrade(String teacherId) {

        TeacherEntity teacher = teacherRepository.getById(teacherId);
        return teacher.getGrade();
    }

    @Override
    public boolean canRelease(String teacherId) {
        TeacherEntity teacher = teacherRepository.getById(teacherId);
        return teacher.getReleaseLab();
    }

    @Override
    public boolean earlierThanDdl(Integer labId) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        LabEntity lab = labRepository.getById(labId);
        Date now = new Date();
        Date ddl;
        try {
            ddl = sdf.parse(lab.getDeadline());
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
        return now.before(ddl);
    }

    @Override
    public boolean isResp(String teacherId) {
        TeacherEntity teacher = teacherRepository.getById(teacherId);
        return teacher.getType() == 0;
    }
}
