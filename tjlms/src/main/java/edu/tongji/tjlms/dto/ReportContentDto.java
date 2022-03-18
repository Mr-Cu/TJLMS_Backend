package edu.tongji.tjlms.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReportContentDto {
    private String labName;
    private String aim;
    private String principle;
    private String step;
    private String result;
    private Boolean mutable;
    private Boolean isChecked;
}
