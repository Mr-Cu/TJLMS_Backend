package edu.tongji.tjlms.service.lab;

import edu.tongji.tjlms.dto.LabDto;
import edu.tongji.tjlms.dto.ScheduleDto;
import edu.tongji.tjlms.model.LabEntity;
import edu.tongji.tjlms.repository.LabRepository;
import edu.tongji.tjlms.repository.TeacherRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class LabServiceImpl implements LabService{
    @Resource
    LabRepository labRepository;
    @Resource
    TeacherRepository teacherRepository;

    @Override
    public List<LabEntity> getAll() {
        return labRepository.findAll();
    }

    @Override
    public Map<String, Object> getAllWithNames() {
        Map<String, Object> map = new HashMap<>();
        List<LabEntity> list = labRepository.findAll();
        if(list.isEmpty())
        {
            return null;
        }
        map.put("labs",list);
        List<String> names = new ArrayList<>();
        for(LabEntity lab:list)
        {
            names.add(teacherRepository.findById(lab.getReleaseTeacher()).get().getName());
        }
        map.put("names",names);
        return map;
    }

    @Override
    public Integer releaseLab(LabDto lab) {
        LabEntity labEntity = new LabEntity();
        labEntity.setName(lab.getName());
        labEntity.setDeadline(lab.getDeadline());
        labEntity.setReleaseTeacher(lab.getReleaseTeacher());
        labEntity.setIntro(lab.getIntro());
        labEntity.setReleaseDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        LabEntity newLab = labRepository.saveAndFlush(labEntity);

        return newLab.getId();
    }

    @Override
    public List<ScheduleDto> getSchedule() {
        List<LabEntity> labs = getAll();
        if(labs.isEmpty())
        {
            return null;
        }
        List<ScheduleDto> ret = new ArrayList<>();
        for(LabEntity lab:labs)
        {
            ret.add(new ScheduleDto(lab.getId(),lab.getName(),lab.getDeadline()));
        }
        return ret;
    }

    @Override
    public LabEntity getById(Integer id) {
        return labRepository.findAllById(id);
    }


}
