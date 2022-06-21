package edu.tongji.tjlms.service.grade;

import edu.tongji.tjlms.dto.FinalGradeDto;
import edu.tongji.tjlms.dto.QueryGradeDto;
import edu.tongji.tjlms.model.CourseEntity;
import edu.tongji.tjlms.model.QueryGradeEntity;
import edu.tongji.tjlms.repository.*;
import edu.tongji.tjlms.service.check.CheckService;
import edu.tongji.tjlms.util.ScoreConvertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QueryGradeServiceImpl implements QueryGradeService {
    @Resource
    QueryGradeRepository queryGradeRepository;

    @Resource
    LabRepository labRepository;

    @Resource
    ReportRepository reportRepository;

    @Resource
    SummatorBasicRepository summatorBasicRepository;

    @Autowired
    CheckService checkService;

    @Resource
    CourseRepository courseRepository;

    @Override
    public QueryGradeEntity queryParticularGrade(String stuId, Integer labId) {
        return queryGradeRepository.findByStuIdAndLabId(stuId,labId);
    }

    @Override
    public List<QueryGradeDto> queryGrade(String id) {
        List<QueryGradeDto> ret = new ArrayList<>();
        List<QueryGradeEntity> grades = queryGradeRepository.findAllByStuId(id);
        for(QueryGradeEntity grade: grades)
        {
            ret.add(new QueryGradeDto(grade, ScoreConvertUtil.score2Grade(grade.getScore())));
        }
        return ret;
    }

    @Override
    public FinalGradeDto queryFinalGrade(String id) {
        FinalGradeDto ret = new FinalGradeDto();
        ret.setEachGrades(queryGrade(id));
        Double attendance = checkService.calculateAttendance(id);
        ret.setAttendance(attendance);
        Optional<CourseEntity> course = courseRepository.findById("420000");
        Double ratio = course.get().getRatio();
        double sum = 0.;
        for (QueryGradeDto grade: ret.getEachGrades())
        {
            sum += grade.getQueryGradeEntity().getScore();
        }
        double avg = 0.;
        if(!ret.getEachGrades().isEmpty())
        {
            avg = sum/ret.getEachGrades().size();
        }
        double finalScore = avg*(1.0-ratio)+ret.getAttendance()*ratio;
        ret.setFinalScore(finalScore);
        ret.setFinalGrade(ScoreConvertUtil.score2Grade(finalScore));
        return ret;
    }

}
