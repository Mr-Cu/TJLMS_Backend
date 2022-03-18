package edu.tongji.tjlms.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FindBackPwdDto {
    private Integer userType;
    private String id;
    private String newPwd;
}
