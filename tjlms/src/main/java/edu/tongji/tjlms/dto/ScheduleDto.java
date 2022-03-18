package edu.tongji.tjlms.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScheduleDto {
    private Integer id;
    private String labName;
    private String deadline;

    public ScheduleDto(Integer id, String labName, String deadline) {
        this.id = id;
        this.labName = labName;
        this.deadline = deadline;
    }
}
