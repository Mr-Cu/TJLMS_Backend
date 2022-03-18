package edu.tongji.tjlms.service.reportfile;

import edu.tongji.tjlms.dto.UploadReportDto;
import edu.tongji.tjlms.model.ReportFileEntity;
import edu.tongji.tjlms.model.ReportFileEntityPK;

import java.util.List;

public interface ReportFileService {
    String saveFile(UploadReportDto urd);
    String submitFile(ReportFileEntityPK pk);
    List<ReportFileEntity> getAllByStuId(String stuId);
    List<ReportFileEntity> getAllByLabId(Integer labId);
    ReportFileEntity getByStuIdAndLabId(String stuId,Integer labId);
}
