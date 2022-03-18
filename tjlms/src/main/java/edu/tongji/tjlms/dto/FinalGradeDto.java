package edu.tongji.tjlms.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FinalGradeDto {
    private List<QueryGradeDto> eachGrades;
    private Double attendance;
    private Double finalScore;
    private String finalGrade;
}
