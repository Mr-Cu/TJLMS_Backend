package edu.tongji.tjlms.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Charles Gao
 * @date 2021/10/10
 * @description class LoginDto(used in API login)
 */
@Getter
@Setter
public class LoginDto {
    private String emailAddress;
    private String password;
    private Integer userType;  // 0->Admin 1->Student 2->Teacher

}

