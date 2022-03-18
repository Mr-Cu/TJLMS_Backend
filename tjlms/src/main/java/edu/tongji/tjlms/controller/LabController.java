package edu.tongji.tjlms.controller;

import edu.tongji.tjlms.dto.LabDto;
import edu.tongji.tjlms.dto.ScheduleDto;
import edu.tongji.tjlms.model.LabEntity;
import edu.tongji.tjlms.service.lab.LabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@ResponseBody
public class LabController {
    @Autowired
    LabService labService;

    @GetMapping("/get/all/labs")
    public ResponseEntity<?> getAllLabs()
    {
        try
        {
            Map<String,Object> labs = labService.getAllWithNames();
            if(labs == null)
            {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("暂无实验信息");
            }
            return ResponseEntity.status(HttpStatus.OK).body(labs);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("数据库请求错误");
        }
    }

    @PostMapping("/release/lab")
    public ResponseEntity<?> releaseLab(@RequestBody LabDto lab)
    {
        try
        {
            Integer ret = labService.releaseLab(lab);
            return ResponseEntity.status(HttpStatus.OK).body(ret);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("数据库请求错误");
        }
    }
    @GetMapping("/get/schedule")
    public ResponseEntity<?> getSchedule()
    {
        try
        {
            List<ScheduleDto> schedule = labService.getSchedule();
            if(schedule == null)
            {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("暂无日程安排");
            }
            return ResponseEntity.status(HttpStatus.OK).body(schedule);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("数据库请求错误");
        }
    }

    @GetMapping("/get/lab/{labId}")
    public ResponseEntity<?> getLabById(@PathVariable("labId") Integer id)
    {
        try
        {
            LabEntity lab = labService.getById(id);
            if(lab == null)
            {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("实验不存在");
            }
            return ResponseEntity.status(HttpStatus.OK).body(lab);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("数据库请求错误");
        }
    }
}
