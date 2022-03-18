package edu.tongji.tjlms.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StuGetCheckDto {
    private int id;
    private String startTime;
    private String endTime;
    private boolean hasChecked;
}
