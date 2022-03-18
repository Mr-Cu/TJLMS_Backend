package edu.tongji.tjlms.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubmitReportDto {
    private String stuId;
    private Integer labId;
    private String aim;
    private String principle;
    private String step;
    private String result;
}
