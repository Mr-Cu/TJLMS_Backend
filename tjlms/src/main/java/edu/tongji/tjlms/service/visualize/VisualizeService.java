package edu.tongji.tjlms.service.visualize;

import edu.tongji.tjlms.model.AllGradeEntity;

import java.util.List;
import java.util.Map;

public interface VisualizeService {
    Map<String,Integer> getSubmissionByClassAndLab(String classId,Integer labId);
    Map<String, Number> getAllGradeByLab(Integer labId);
    Map<String, Number> getGradeByClassAndLab(String classId,Integer labId);
    Map<String,Number> handleData(List<AllGradeEntity> list);
}
