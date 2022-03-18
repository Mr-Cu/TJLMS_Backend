package edu.tongji.tjlms.service.sumreport;


import edu.tongji.tjlms.dto.ReportInfoDto;
import edu.tongji.tjlms.dto.SubmitSummatorDto;
import edu.tongji.tjlms.dto.SummatorResultDto;
import edu.tongji.tjlms.model.SummatorBasicEntity;
import edu.tongji.tjlms.model.SummatorResultEntity;
import edu.tongji.tjlms.repository.SummatorBasicRepository;
import edu.tongji.tjlms.repository.SummatorResultRepository;
import edu.tongji.tjlms.repository.TakesRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class SummatorReportServiceImpl implements SummatorReportService{
    @Resource
    SummatorBasicRepository summatorBasicRepository;

    @Resource
    SummatorResultRepository summatorResultRepository;

    @Resource
    TakesRepository takesRepository;
    @Override
    public String saveReport(SubmitSummatorDto ssd) {
        SummatorBasicEntity sbe = new SummatorBasicEntity();
        sbe.setAim(ssd.getSummatorBasicDto().getAim());
        sbe.setMutable(true);
        sbe.setStuId(ssd.getSummatorBasicDto().getStuId());
        sbe.setUpdateDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        sbe.setClassId(takesRepository.findByStuId(ssd.getSummatorBasicDto().getStuId()).getClassId());
        sbe.setPrinciple(ssd.getSummatorBasicDto().getPrinciple());
        sbe.setStep(ssd.getSummatorBasicDto().getStep());
        sbe.setIsChecked(false);
        sbe.setConclusion(ssd.getSummatorBasicDto().getConclusion());
        summatorBasicRepository.save(sbe);

        List<SummatorResultEntity> list = new ArrayList<>();
        for(SummatorResultDto srd:ssd.getResultList())
        {
            SummatorResultEntity temp = new SummatorResultEntity(srd);
            temp.setStuId(ssd.getSummatorBasicDto().getStuId());
            list.add(temp);
        }
        summatorResultRepository.saveAll(list);
        return "实验报告暂存成功";
    }

    @Override
    public String submitReport(SubmitSummatorDto ssd) {
            SummatorBasicEntity sbe = new SummatorBasicEntity();
            sbe.setAim(ssd.getSummatorBasicDto().getAim());
            sbe.setMutable(false);
            sbe.setStuId(ssd.getSummatorBasicDto().getStuId());
            sbe.setUpdateDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            sbe.setClassId(takesRepository.findByStuId(ssd.getSummatorBasicDto().getStuId()).getClassId());
            sbe.setPrinciple(ssd.getSummatorBasicDto().getPrinciple());
            sbe.setStep(ssd.getSummatorBasicDto().getStep());
            sbe.setIsChecked(false);
            sbe.setConclusion(ssd.getSummatorBasicDto().getConclusion());
            summatorBasicRepository.save(sbe);

            List<SummatorResultEntity> list = new ArrayList<>();
            for(SummatorResultDto srd:ssd.getResultList())
            {
                SummatorResultEntity temp = new SummatorResultEntity(srd);
                temp.setStuId(ssd.getSummatorBasicDto().getStuId());
                list.add(temp);
            }
            summatorResultRepository.saveAll(list);
        return "提交成功";
    }

    @Override
    public ReportInfoDto getInfo(String id) {
        Optional<SummatorBasicEntity> basic = summatorBasicRepository.findById(id);
        if(basic.isPresent())
        {
            ReportInfoDto rid = new ReportInfoDto();
            rid.setIsChecked(basic.get().getIsChecked());
            rid.setLabName("加法器实验");
            rid.setMutable(basic.get().getMutable());
            rid.setUpdateDate(basic.get().getUpdateDate());
            return rid;
        }
        else
        {
            return null;
        }
    }

    @Override
    public Map<String, Object> getContent(String id) {
        Optional<SummatorBasicEntity> basic = summatorBasicRepository.findById(id);
        List<SummatorResultEntity> result = summatorResultRepository.findAllByStuId(id);
        Map<String,Object> map = new HashMap<>();
        if(basic.isPresent())
        {
            map.put("basic",basic);
        }
        else
        {
            map.put("basic",null);
        }
        map.put("result",result);
        return map;
    }
}
