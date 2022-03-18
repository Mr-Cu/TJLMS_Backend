package edu.tongji.tjlms.service.report;

import edu.tongji.tjlms.dto.ReportContentDto;
import edu.tongji.tjlms.dto.ReportInfoDto;
import edu.tongji.tjlms.dto.SubmitReportDto;
import edu.tongji.tjlms.model.ReportEntityPK;

import java.util.List;

public interface ReportService {
    String saveReport(SubmitReportDto report);

    String submitReport(SubmitReportDto reportDto);

    List<ReportInfoDto> getInfo(String id);

    List<ReportContentDto> getContent(ReportEntityPK reportEntityPK);

}
