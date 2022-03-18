package edu.tongji.tjlms.service.material;

import edu.tongji.tjlms.dto.UploadMaterialDto;
import edu.tongji.tjlms.model.MaterialEntity;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface MaterialService {
    String uploadMaterial(UploadMaterialDto umd);
    String deleteMaterial(Integer id);

    Map<String,Object> getAllMaterials(Integer pageNum, Integer pageSize);

    List<MaterialEntity> getAllByLabId(Integer labId);
    Map<String,Object> getAllByLabId(Integer labId, Integer pageNum,Integer pageSize);

    Page<MaterialEntity> getAllByUploader(String uploader,Integer pageNum,Integer pageSize);

    List<MaterialEntity> getLatestFive();
}
