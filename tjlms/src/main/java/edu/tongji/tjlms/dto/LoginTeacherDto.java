package edu.tongji.tjlms.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author CHUYANG DONG
 * @date 2022/6/22
 * @description class LoginAdminDto(used in API login)
 */
@Getter
@Setter
public class LoginTeacherDto {
    private String id;
    private String emailAddr;
    private String name;
    private Boolean verified;
    private Integer type;
    private Boolean grade;
    private Boolean releaseLab;

}

