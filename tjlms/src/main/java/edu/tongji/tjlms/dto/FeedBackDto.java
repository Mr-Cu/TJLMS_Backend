package edu.tongji.tjlms.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FeedBackDto {
    private String feedbacker;
    private String title;
    private String content;
    private Boolean isAnonymous;
}
