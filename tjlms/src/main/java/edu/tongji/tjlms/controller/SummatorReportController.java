package edu.tongji.tjlms.controller;

import edu.tongji.tjlms.dto.ReportInfoDto;
import edu.tongji.tjlms.dto.SubmitSummatorDto;
import edu.tongji.tjlms.service.sumreport.SummatorReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@ResponseBody
@RequestMapping("/api")
public class SummatorReportController {
    @Autowired
    SummatorReportService summatorReportService;

    @PostMapping("/save/summator")
    ResponseEntity<String> postSummator(@RequestBody SubmitSummatorDto ssd)
    {
        try
        {
            return ResponseEntity.status(HttpStatus.OK).body(summatorReportService.saveReport(ssd));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("数据库请求错误");
        }

    }

    @PostMapping("/submit/summator")
    ResponseEntity<String> submitSummator(@RequestBody SubmitSummatorDto ssd)
    {
        try
        {
            return ResponseEntity.status(HttpStatus.OK).body(summatorReportService.submitReport(ssd));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("数据库请求错误");
        }

    }

    @GetMapping("/get/summator/info")
    ResponseEntity<?> getSummatorInfo(String id)
    {
        try
        {
            ReportInfoDto ret = summatorReportService.getInfo(id);
            if(ret != null)
            {
                return ResponseEntity.status(HttpStatus.OK).body(ret);
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("暂无实验报告信息");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("数据库请求错误");
        }
    }

    @GetMapping("/get/summator/content")
    ResponseEntity<?> getSummatorContent(String id)
    {
        try
        {
            Map<String,Object> map = summatorReportService.getContent(id);
            return ResponseEntity.status(HttpStatus.OK).body(map);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("数据库请求错误");
        }
    }





}
