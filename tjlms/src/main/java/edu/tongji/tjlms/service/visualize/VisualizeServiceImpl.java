package edu.tongji.tjlms.service.visualize;

import edu.tongji.tjlms.model.AllGradeEntity;
import edu.tongji.tjlms.model.ReportListEntity;
import edu.tongji.tjlms.repository.AllGradeRepository;
import edu.tongji.tjlms.repository.ReportListRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class VisualizeServiceImpl implements VisualizeService{

    @Resource
    ReportListRepository reportListRepository;

    @Resource
    AllGradeRepository allGradeRepository;
    @Override
    public Map<String, Integer> getSubmissionByClassAndLab(String classId, Integer labId) {
        List<ReportListEntity> list = reportListRepository.findAllByClassIdAndLabId(classId,labId);
        if(list.isEmpty())
        {
            return null;
        }
        Integer notSubmitted = 0;
        Integer submitted = 0;
        Integer isChecked = 0;
        if(list.get(0).getMutable() == null || list.get(0).getMutable())
        {
            notSubmitted++;
        }
        else
        {
            submitted++;
        }
        if(list.get(0).getChecked()!= null && list.get(0).getChecked())
        {
            isChecked++;
        }
        for(int i = 1;i<list.size();i++)
        {
            if(list.get(i).getStuId().equals(list.get(i-1).getStuId()) && list.get(i).getLabId() == list.get(i-1).getLabId())
            {
                continue;
            }
            if(list.get(i).getMutable() == null || list.get(i).getMutable())
            {
                notSubmitted++;
            }
            else
            {
                submitted++;
            }
            if(list.get(0).getChecked()!= null && list.get(0).getChecked())
            {
                isChecked++;
            }
        }
        Map<String,Integer> map = new HashMap<>();
        map.put("notSubmitted",notSubmitted);
        map.put("submitted",submitted);
        map.put("checked",isChecked);
        return map;
    }

    @Override
    public Map<String, Number> getAllGradeByLab(Integer labId) {
        List<AllGradeEntity> list = allGradeRepository.findAllByLabId(labId);
        return handleData(list);
    }

    @Override
    public Map<String, Number> getGradeByClassAndLab(String classId, Integer labId) {
        List<AllGradeEntity> list = allGradeRepository.findAllByClassIdAndLabId(classId, labId);
        return handleData(list);
    }

    @Override
    public Map<String, Number> handleData(List<AllGradeEntity> list) {
        if(list.isEmpty())
        {
            return null;
        }
        Double max = list.get(0).getScore();
        Double min = list.get(0).getScore();
        Double sum = 0.;
        Integer numA = 0;
        Integer numB = 0;
        Integer numC = 0;
        Integer numD = 0;
        Integer numF = 0;
        for(AllGradeEntity grade: list)
        {
            if(grade.getScore()>=90)
            {
                numA++;
            }
            else if(grade.getScore()>=80)
            {
                numB++;
            }
            else if(grade.getScore()>=70)
            {
                numC++;
            }
            else if(grade.getScore()>=60)
            {
                numD++;
            }
            else
            {
                numF++;
            }
            sum+= grade.getScore();
            if(grade.getScore()>max)
            {
                max = grade.getScore();
            }
            if(grade.getScore()<min)
            {
                min = grade.getScore();
            }
        }
        Double avg = sum / list.size();
        Map<String,Number> map = new HashMap<>();
        map.put("high",max);
        map.put("low",min);
        map.put("average",avg);
        map.put("numA",numA);
        map.put("numB",numB);
        map.put("numC",numC);
        map.put("numD",numD);
        map.put("numF",numF);
        return map;
    }
}
