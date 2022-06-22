package edu.tongji.tjlms.service.check;

import edu.tongji.tjlms.dto.StuGetCheckDto;
import edu.tongji.tjlms.dto.PostCheckDto;
import edu.tongji.tjlms.dto.TeacherGetCheckDto;
import edu.tongji.tjlms.model.CheckEntity;
import edu.tongji.tjlms.model.ClassEntity;
import edu.tongji.tjlms.model.StuCheckEntity;
import edu.tongji.tjlms.model.TakesEntity;
import edu.tongji.tjlms.repository.CheckRepository;
import edu.tongji.tjlms.repository.ClassRepository;
import edu.tongji.tjlms.repository.StuCheckRepository;
import edu.tongji.tjlms.repository.TakesRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CheckServiceImpl implements CheckService{
    @Resource
    ClassRepository classRepository;

    @Resource
    CheckRepository checkRepository;

    @Resource
    TakesRepository takesRepository;

    @Resource
    StuCheckRepository stuCheckRepository;

    @Override
    public String postCheck(PostCheckDto pcd) {
        if(pcd.getClassId()==null){
            return "班级编号不能为空";
        }
        ClassEntity class1 = classRepository.findAllById(pcd.getClassId());
        if(class1==null){
            return "不存在该班级";
        }
        CheckEntity check = new CheckEntity();
        check.setClassId(pcd.getClassId());
        check.setStartTime(pcd.getStart());
        check.setEndTime(pcd.getEnd());
        check.setCheckedStudent(0);
        CheckEntity check1 = checkRepository.saveAndFlush(check);
        int checkId = check1.getId();
        List<TakesEntity> takes = takesRepository.findAllByClassId(pcd.getClassId());
        for(TakesEntity takes1:takes) {
            StuCheckEntity stuCheck = new StuCheckEntity();
            stuCheck.setStuId(takes1.getStuId());
            stuCheck.setCheckId(checkId);
            stuCheck.setTime(null);
            stuCheckRepository.save(stuCheck);
        }
        return "考勤发布成功";
    }

    @Override
    public List<StuGetCheckDto> getAllCheckByStuId(String stuId) {
        String classId=takesRepository.findByStuId(stuId).getClassId();
        if(classId==null){
            return null;
        }
        List<CheckEntity> checks = checkRepository.findAllByClassId(classId);
        List<StuGetCheckDto> ret = new ArrayList<>();
        if(checks.isEmpty()) {
            return null;
        }
        for(CheckEntity check:checks) {
            StuGetCheckDto getCheckDto = new StuGetCheckDto();
            getCheckDto.setId(check.getId());
            getCheckDto.setStartTime(check.getStartTime());
            getCheckDto.setEndTime(check.getEndTime());
            boolean checked = (stuCheckRepository.findByStuIdAndCheckId(stuId,check.getId()).getTime() != null);
            getCheckDto.setHasChecked(checked);
            ret.add(getCheckDto);
        }
        return ret;
    }

    @Override
    public String submitCheck(String stuId,Integer checkId) {
        if(stuId == null){
            return "学生编号不能为空";
        }
        if(checkId == null){
            return "签到编号不能为空";
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Optional<CheckEntity> check = checkRepository.findById(checkId);
        String start = check.get().getStartTime();
        String end = check.get().getEndTime();
        Date dateStart;
        Date dateEnd;
        Date now = new Date();
        try {
            dateStart = sdf.parse(start);
        } catch (ParseException e) {
            e.printStackTrace();
            return "时间转换失败";
        }
        try {
            dateEnd = sdf.parse(end);
        } catch (ParseException e) {
            e.printStackTrace();
            return "时间转换失败";
        }
        if(now.before(dateStart)) {
            return "未到签到时间，无法签到";
        }
        else if(now.after(dateEnd)) {
            return "签到已结束，无法签到";
        }
        else {
            StuCheckEntity stuCheck = new StuCheckEntity();
            stuCheck.setCheckId(checkId);
            stuCheck.setStuId(stuId);
            stuCheck.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            stuCheckRepository.save(stuCheck);
            return "签到成功";
        }
    }

    @Override
    public Double calculateAttendance(String stuId) {
        double ret = 0.;
        int valid = 0;
        String classId = takesRepository.findByStuId(stuId).getClassId();
        List<CheckEntity> checks = checkRepository.findAllByClassId(classId);
        List<StuCheckEntity> attendances = stuCheckRepository.findAllByStuId(stuId);
        for(StuCheckEntity stuCheck:attendances) {
            if(stuCheck.getTime() != null) {
                valid++;
            }
        }
        if(!checks.isEmpty()) {
            ret = (double) valid/checks.size();
        }
        return ret*100.0;
    }

    @Override
    public List<TeacherGetCheckDto> getAllCheckByClassId(String classId) {
        List<TeacherGetCheckDto> list = new ArrayList<>();
        List<CheckEntity> checks = checkRepository.findAllByClassId(classId);
        int all = takesRepository.findAllByClassId(classId).size();
        for(CheckEntity check:checks) {
            int checked = 0;
            TeacherGetCheckDto teacherGetCheckDto = new TeacherGetCheckDto();
            teacherGetCheckDto.setId(check.getId());
            teacherGetCheckDto.setAll(all);
            teacherGetCheckDto.setEndTime(check.getEndTime());
            teacherGetCheckDto.setStartTime(check.getStartTime());
            List<StuCheckEntity> stuCheckEntities = stuCheckRepository.findAllByCheckId(check.getId());
            for(StuCheckEntity stuCheck:stuCheckEntities) {
                if(stuCheck.getTime() != null) {
                    checked++;
                }
            }
            teacherGetCheckDto.setChecked(checked);
            list.add(teacherGetCheckDto);
        }
        if(list.isEmpty()) {
            return null;
        }
        return list;
    }
}
