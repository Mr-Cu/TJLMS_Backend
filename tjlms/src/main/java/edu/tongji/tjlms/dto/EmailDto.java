package edu.tongji.tjlms.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Charles Gao
 * @date 2021/10/10
 * @description class EmailDto(used in sending email API)
 */
@Getter
@Setter
public class EmailDto {
    private String id;
    private String emailAddress;
    private Integer userType;

}
