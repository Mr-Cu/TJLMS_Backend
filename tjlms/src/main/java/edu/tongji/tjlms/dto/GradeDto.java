package edu.tongji.tjlms.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GradeDto {
    private String stuId;
    private String classId;
    private String teacherId;
    private Integer labId;
    private Double score;
    private String note;

}
