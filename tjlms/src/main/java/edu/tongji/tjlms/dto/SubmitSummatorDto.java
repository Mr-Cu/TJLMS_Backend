package edu.tongji.tjlms.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SubmitSummatorDto {
    private SummatorBasicDto summatorBasicDto;
    private List<SummatorResultDto> resultList;
}
