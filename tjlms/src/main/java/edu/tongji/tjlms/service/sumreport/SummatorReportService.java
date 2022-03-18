package edu.tongji.tjlms.service.sumreport;

import edu.tongji.tjlms.dto.ReportInfoDto;
import edu.tongji.tjlms.dto.SubmitSummatorDto;

import java.util.Map;

public interface SummatorReportService {
    String saveReport(SubmitSummatorDto ssd);

    String submitReport(SubmitSummatorDto ssd);

    ReportInfoDto getInfo(String id);

    Map<String,Object> getContent(String id);

}
