package edu.tongji.tjlms.controller;

import edu.tongji.tjlms.dto.ReportContentDto;
import edu.tongji.tjlms.dto.ReportInfoDto;
import edu.tongji.tjlms.dto.SubmitReportDto;
import edu.tongji.tjlms.model.ReportEntityPK;
import edu.tongji.tjlms.service.report.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@ResponseBody
public class ReportController {
    @Autowired
    ReportService reportService;

    @PostMapping("/post/save/report")
    public ResponseEntity<String> saveReport(@RequestBody SubmitReportDto reportDto)
    {
        try
        {
            return ResponseEntity.status(HttpStatus.OK).body(reportService.saveReport(reportDto));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("数据库请求错误");
        }
    }
    @PostMapping("/post/submit/report")
    public ResponseEntity<String> submitReport(@RequestBody SubmitReportDto reportDto)
    {
        try
        {
            String ret = reportService.submitReport(reportDto);
            return ret.equals("提交成功") ? ResponseEntity.status(HttpStatus.OK).body(ret) :
                    ResponseEntity.status(HttpStatus.NOT_FOUND).body(ret);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("数据库请求错误");
        }
    }

    @GetMapping("/get/report/info")
    public ResponseEntity<?> getInfo(String id)
    {
        try
        {
            List<ReportInfoDto> ret = reportService.getInfo(id);
            if(ret == null)
            {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("暂无实验报告信息");
            }
            return ResponseEntity.status(HttpStatus.OK).body(ret);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("数据库请求错误");
        }

    }

    @PostMapping("/post/report/content")
    public ResponseEntity<?> getContent(@RequestBody ReportEntityPK reportEntityPK)
    {
        try
        {
            List<ReportContentDto> ret = reportService.getContent(reportEntityPK);
            if(ret == null)
            {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("暂无信息");
            }
            return ResponseEntity.status(HttpStatus.OK).body(ret);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("数据库请求错误");
        }

    }




}
