package edu.tongji.tjlms.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Charles Gao
 * @date 2021/10/10
 * @description class SignInDto(used in sign in API)
 */
@Getter
@Setter
public class SignInDto {
    private Integer userType;
    private String id;
    private String emailAddress;
    private String password;

}
