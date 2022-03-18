package edu.tongji.tjlms.service.lab;

import edu.tongji.tjlms.dto.LabDto;
import edu.tongji.tjlms.dto.ScheduleDto;
import edu.tongji.tjlms.model.LabEntity;

import java.util.List;
import java.util.Map;

public interface LabService {
    List<LabEntity> getAll();
    Map<String, Object> getAllWithNames();
    Integer releaseLab(LabDto lab);
    List<ScheduleDto> getSchedule();
    LabEntity getById(Integer id);
}
