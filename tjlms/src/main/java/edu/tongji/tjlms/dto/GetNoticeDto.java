package edu.tongji.tjlms.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetNoticeDto {
    private Integer id;
    private String title;
    private String time;
    private String teacherName;

    public GetNoticeDto(Integer id, String title,String time,String teacherName) {
        this.id = id;
        this.title = title;
        this.time = time;
        this.teacherName = teacherName;
    }
}
