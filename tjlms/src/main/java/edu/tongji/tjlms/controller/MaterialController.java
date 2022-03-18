package edu.tongji.tjlms.controller;


import edu.tongji.tjlms.dto.UploadMaterialDto;
import edu.tongji.tjlms.model.MaterialEntity;
import edu.tongji.tjlms.service.material.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@ResponseBody
public class MaterialController {
    @Autowired
    MaterialService materialService;

    Integer countAll;
    Integer countLab;
    Integer countUploader;
    @PostMapping("/post/material")
    public ResponseEntity<String> uploadMaterial(@RequestBody UploadMaterialDto material)
    {
        try
        {
            return ResponseEntity.status(HttpStatus.OK).body(materialService.uploadMaterial(material));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("数据库请求错误");
        }
    }

    @PostMapping("/delete/material/{materialId}")
    public ResponseEntity<String> deleteMaterial(@PathVariable("materialId") Integer id)
    {
        try
        {
            return ResponseEntity.status(HttpStatus.OK).body(materialService.deleteMaterial(id));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("数据库请求错误");
        }
    }

    @GetMapping("/get/all/material")
    public ResponseEntity<?> getAllMaterials(@RequestParam(defaultValue = "1") Integer pageNum,
                                             @RequestParam(defaultValue = "20") Integer pageSize)
    {
        try
        {
            if(pageNum == null || pageNum == 0)
            {
                pageNum = 1;
            }
            if(countAll != null && pageNum >= countAll)
            {
                pageNum = countAll;
            }
            Map<String,Object> map = materialService.getAllMaterials(pageNum,pageSize);
            if(map == null)
            {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("暂无教学资料");
            }
            return ResponseEntity.status(HttpStatus.OK).body(map);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("数据库请求错误");
        }
    }

    @GetMapping("/get/lab/material/page/{labId}")
    public ResponseEntity<?> getAllMaterialsByLabId(@PathVariable("labId") Integer id,
                                             @RequestParam(defaultValue = "1") Integer pageNum,
                                             @RequestParam(defaultValue = "20") Integer pageSize)
    {
        try
        {
            if(pageNum == null || pageNum == 0)
            {
                pageNum = 1;
            }
            if(countLab != null && pageNum >= countLab)
            {
                pageNum = countLab;
            }
            Map<String,Object> map = materialService.getAllByLabId(id,pageNum,pageSize);
            if(map == null)
            {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("暂无该实验相关教学资料");
            }
            return ResponseEntity.status(HttpStatus.OK).body(map);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("数据库请求错误");
        }
    }

    @GetMapping("/get/lab/material/{labId}")
    public ResponseEntity<?> getAllMaterialsByLabId(@PathVariable("labId") Integer id)
    {
        try
        {
            List<MaterialEntity> list = materialService.getAllByLabId(id);
            if(list.isEmpty())
            {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("暂无该实验相关教学资料");
            }
            return ResponseEntity.status(HttpStatus.OK).body(list);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("数据库请求错误");
        }
    }

    @GetMapping("/get/uploader/material/{teacherId}")
    public ResponseEntity<?> getAllMaterialByUploader(@PathVariable("teacherId") String id,
                                                      @RequestParam(defaultValue = "1") Integer pageNum,
                                                      @RequestParam(defaultValue = "20") Integer pageSize)
    {
        try
        {
            if(pageNum == null || pageNum == 0)
            {
                pageNum = 1;
            }
            if(countUploader != null && pageNum >= countUploader)
            {
                pageNum = countLab;
            }
            Page<MaterialEntity> page = materialService.getAllByUploader(id,pageNum,pageSize);
            if(page.isEmpty())
            {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("暂无您上传的教学资料");
            }
            Map<String, Object> map = new HashMap<>();
            countLab = page.getTotalPages();
            map.put("data",page);
            map.put("count",countLab);
            return ResponseEntity.status(HttpStatus.OK).body(map);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("数据库请求错误");
        }
    }

    @GetMapping("/get/latest/material")
    public ResponseEntity<?> getLatest()
    {
        try
        {
            List<MaterialEntity> latest = materialService.getLatestFive();
            if(latest.isEmpty())
            {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("暂无最新教学资料");
            }
            return ResponseEntity.status(HttpStatus.OK).body(latest);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("数据库请求错误");
        }
    }

}
