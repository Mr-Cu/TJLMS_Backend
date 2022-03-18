package edu.tongji.tjlms.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeacherGetCheckDto {
    private int id;
    private String startTime;
    private String endTime;
    private int checked;
    private int all;
}
