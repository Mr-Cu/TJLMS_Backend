package edu.tongji.tjlms.service.grade;

import edu.tongji.tjlms.dto.GradeDto;
import edu.tongji.tjlms.model.*;
import edu.tongji.tjlms.repository.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class GradeServiceImpl implements GradeService{

    @Resource
    ReportListRepository reportListRepository;

    @Resource
    ReportRepository reportRepository;

    @Resource
    ReportFileRepository reportFileRepository;

    @Resource
    LabGradeRepository labGradeRepository;

    @Resource
    ClassRepository classRepository;

    @Resource
    SummatorResultRepository summatorResultRepository;

    @Resource
    SummatorBasicRepository summatorBasicRepository;

    @Resource
    SummatorListRepository summatorListRepository;

    @Override
    public List<ReportListEntity> getReportList(String teacherId) {
        return reportListRepository.findAllByTeacherId(teacherId);
    }


    @Override
    public Map<String,Object> getReport(ReportEntityPK pk) {
        Map<String,Object> map = new HashMap<>();
        map.put("content",reportRepository.findByStuIdAndLabId(pk.getStuId(),pk.getLabId()));
        map.put("file",reportFileRepository.findByStuIdAndLabId(pk.getStuId(),pk.getLabId()));

        return map;
    }

    @Override
    public Map<String, Object> getSummator(String id) {
        Map<String,Object> map = new HashMap<>();
        Optional<SummatorBasicEntity> basic = summatorBasicRepository.findByStuId(id);
        if(basic.isPresent())
        {
            map.put("basic",basic.get());
        }
        else
        {
            map.put("basic",null);
        }
        List<SummatorResultEntity> result = summatorResultRepository.findAllByStuId(id);
        map.put("result",result);
        map.put("file",reportFileRepository.findByStuIdAndLabId(id,1));
        return map;
    }

    @Override
    public List<ClassEntity> getMyClasses(String teacherId) {
        return classRepository.findAllByTeacherIdOrRespIdOrAssistId(teacherId,teacherId,teacherId);
    }

    @Override
    public Page<ReportListEntity> getReportListPaged(String teacherId, Integer pageNum, Integer pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNum-1,pageSize);
        return reportListRepository.findAllByTeacherId(teacherId,pageRequest);
    }

    @Override
    public Page<SummatorListEntity> getSummatorListPaged(String teacherId, Integer pageNum, Integer pageSize) {
        return summatorListRepository.findAllByTeacherId(teacherId, PageRequest.of(pageNum-1,pageSize));
    }
    @Override
    public List<ReportListEntity> getReportByTeacherIdAndLabId(String teacherId, Integer labId) {
        return reportListRepository.findAllByTeacherIdAndLabId(teacherId, labId);
    }

    @Override
    public LabGradeEntity getParticularGrade(String stuId, Integer labId) {
        return labGradeRepository.findByStuIdAndLabId(stuId,labId);
    }

    @Override
    public String save(GradeDto info) {
        LabGradeEntity grade = new LabGradeEntity();
        grade.setClassId(info.getClassId());
        grade.setLabId(info.getLabId());
        grade.setScore(info.getScore());
        grade.setNote(info.getNote());
        grade.setVisible(false);
        grade.setStuId(info.getStuId());
        grade.setTeacherId(info.getTeacherId());
        grade.setUpdateDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        if (info.getLabId() != 1) {
            reportRepository.updateChecked(info.getStuId(), info.getLabId());
        } else {
            summatorBasicRepository.updateCheck(info.getStuId());
        }
        labGradeRepository.save(grade);

        return "暂存成功";
    }


    @Override
    public String release(String classId) {
        String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        labGradeRepository.release(classId,time);
        return "发布成功";
    }

    @Override
    public String release(GradeDto info) {
            LabGradeEntity grade = new LabGradeEntity();
            grade.setClassId(info.getClassId());
            grade.setLabId(info.getLabId());
            grade.setScore(info.getScore());
            grade.setNote(info.getNote());
            grade.setVisible(true);
            grade.setStuId(info.getStuId());
            grade.setTeacherId(info.getTeacherId());
            grade.setUpdateDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            if (info.getLabId() != 1) {
                reportRepository.updateChecked(info.getStuId(), info.getLabId());
            } else {
                summatorBasicRepository.updateCheck(info.getStuId());
            }
            labGradeRepository.save(grade);
        return "发布成功";
    }
}
