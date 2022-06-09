package edu.tongji.tjlms.service.minio;

import edu.tongji.tjlms.dto.AddFileDto;
import org.springframework.web.multipart.MultipartFile;

public interface MinIOService {
    String uploadFile(MultipartFile file);
    byte[] getFile(String fileName);
    String removeFile(String fileName);
    void addToFile(AddFileDto addFile);
    void ReplaceFile(AddFileDto addFile);

}
