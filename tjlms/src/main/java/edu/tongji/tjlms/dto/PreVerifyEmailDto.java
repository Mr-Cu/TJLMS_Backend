package edu.tongji.tjlms.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PreVerifyEmailDto {
    private String id;
    private String email;
    private int userType;
}
