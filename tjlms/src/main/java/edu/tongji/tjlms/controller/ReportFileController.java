package edu.tongji.tjlms.controller;

import edu.tongji.tjlms.dto.UploadReportDto;
import edu.tongji.tjlms.model.ReportFileEntity;
import edu.tongji.tjlms.model.ReportFileEntityPK;
import edu.tongji.tjlms.service.reportfile.ReportFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@ResponseBody
public class ReportFileController {
    @Autowired
    ReportFileService reportFileService;

    @PostMapping("/save/report/file")
    ResponseEntity<String> saveReportFile(@RequestBody UploadReportDto urd)
    {
        try
        {
            return ResponseEntity.status(HttpStatus.OK).body(reportFileService.saveFile(urd));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("数据库请求错误");
        }
    }

    @PostMapping("/submit/report/file")
    ResponseEntity<String> submitReportFile(@RequestBody ReportFileEntityPK pk)
    {
        try
        {
            return ResponseEntity.status(HttpStatus.OK).body(reportFileService.submitFile(pk));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("数据库请求错误");
        }
    }

    @GetMapping("/get/reportFileStudent/{studentId}")
    ResponseEntity<?> getAllByStuId(@PathVariable("studentId") String studentId)
    {
        try
        {
            List<ReportFileEntity> list = reportFileService.getAllByStuId(studentId);
            if(list.isEmpty())
            {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("暂无提交文件");
            }
            return ResponseEntity.status(HttpStatus.OK).body(list);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("数据库请求错误");
        }
    }

    @GetMapping("/get/reportFileLab/{labId}")
    ResponseEntity<?> getAllByStuId(@PathVariable("labId") Integer labId)
    {
        try
        {
            List<ReportFileEntity> list = reportFileService.getAllByLabId(labId);
            if(list.isEmpty())
            {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("暂无提交文件");
            }
            return ResponseEntity.status(HttpStatus.OK).body(list);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("数据库请求错误");
        }
    }

    @GetMapping("/get/reportFilePK")
    ResponseEntity<?> getByPK(String stuId,Integer labId)
    {
        try {
            ReportFileEntity file = reportFileService.getByStuIdAndLabId(stuId,labId);
            if(file == null)
            {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("文件不存在");
            }
            return ResponseEntity.status(HttpStatus.OK).body(file);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("数据库请求错误");
        }

    }
}
