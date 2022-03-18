package edu.tongji.tjlms.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UploadReportDto {
    private String stuId;
    private Integer labId;
    private String location;
    private String name;
}
