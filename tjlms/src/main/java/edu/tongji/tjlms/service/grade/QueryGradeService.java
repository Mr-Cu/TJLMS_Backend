package edu.tongji.tjlms.service.grade;

import edu.tongji.tjlms.dto.FinalGradeDto;
import edu.tongji.tjlms.dto.QueryGradeDto;
import edu.tongji.tjlms.model.QueryGradeEntity;

import java.util.List;

public interface QueryGradeService {
    QueryGradeEntity queryParticularGrade(String stuId,Integer labId);
    List<QueryGradeDto> queryGrade(String id);
    FinalGradeDto queryFinalGrade(String id);
}
