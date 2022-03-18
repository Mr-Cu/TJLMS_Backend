package edu.tongji.tjlms.service.reportfile;

import edu.tongji.tjlms.dto.UploadReportDto;
import edu.tongji.tjlms.model.ReportFileEntity;
import edu.tongji.tjlms.model.ReportFileEntityPK;
import edu.tongji.tjlms.repository.ReportFileRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class ReportFileServiceImpl implements ReportFileService{

    @Resource
    ReportFileRepository reportFileRepository;
    @Override
    public String saveFile(UploadReportDto urd) {
        ReportFileEntity file = new ReportFileEntity();
        file.setLabId(urd.getLabId());
        file.setMutable(true);
        file.setLocation(urd.getLocation());
        file.setStuId(urd.getStuId());
        file.setName(urd.getName());
        file.setUploadTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        reportFileRepository.save(file);
        return "文件暂存成功";
    }

    @Override
    public String submitFile(ReportFileEntityPK pk) {
        ReportFileEntity file = reportFileRepository.findByStuIdAndLabId(pk.getStuId(),pk.getLabId());
        file.setUploadTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        file.setMutable(false);
        return "文件提交成功";
    }

    @Override
    public List<ReportFileEntity> getAllByStuId(String stuId) {

        return reportFileRepository.findAllByStuId(stuId);
    }

    @Override
    public List<ReportFileEntity> getAllByLabId(Integer labId) {
        return reportFileRepository.findAllByLabId(labId);
    }

    @Override
    public ReportFileEntity getByStuIdAndLabId(String stuId, Integer labId) {
        return reportFileRepository.findByStuIdAndLabId(stuId,labId);
    }
}
