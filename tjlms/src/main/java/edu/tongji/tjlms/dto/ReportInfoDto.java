package edu.tongji.tjlms.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReportInfoDto {
    private String labName;
    private String updateDate;
    private Boolean mutable;
    private Boolean isChecked;
}
