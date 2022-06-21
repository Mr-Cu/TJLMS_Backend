package edu.tongji.tjlms.controller;

import edu.tongji.tjlms.dto.AddFileDto;
import edu.tongji.tjlms.service.minio.MinIOService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/minIO")
@ResponseBody
@Api(value = "minIO", tags = "minIO", description = "上传文件、图片")
public class MinIOController {
    @Autowired
    MinIOService uploader;

//    @ApiOperation(value = "minIO")
    @PostMapping("/uploadFile")
//    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        return ResponseEntity.status(HttpStatus.OK).body(uploader.uploadFile(file));
    }

    @ApiOperation(value = "minIO")
    @RequestMapping(value = "/get/{fileName}", method = RequestMethod.GET)
    public byte[] getFile(@PathVariable("fileName") String fileName) {
        return uploader.getFile(fileName);
    }

    @ApiOperation(value = "minIO")
    @RequestMapping(value = "/remove/{fileName}", method = RequestMethod.GET)
    public String removeFile(@PathVariable("fileName") String fileName) {
        return uploader.removeFile(fileName);
    }

    @ApiOperation(value = "minIO")
    @RequestMapping(value = "/addToFile", method = RequestMethod.POST)
    public void addToFile(@RequestBody AddFileDto addFile) {
        uploader.addToFile(addFile);
    }

    @ApiOperation(value = "minIO")
    @RequestMapping(value = "/replaceFile", method = RequestMethod.POST)
    public void replaceFile(@RequestBody AddFileDto addFile) {
        uploader.ReplaceFile(addFile);
    }

}
