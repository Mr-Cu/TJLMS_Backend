package edu.tongji.tjlms.service.material;

import edu.tongji.tjlms.dto.UploadMaterialDto;
import edu.tongji.tjlms.model.MaterialEntity;
import edu.tongji.tjlms.repository.MaterialRepository;
import edu.tongji.tjlms.repository.TeacherRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class MaterialServiceImpl implements MaterialService{
    @Resource
    MaterialRepository materialRepository;

    @Resource
    TeacherRepository teacherRepository;
    @Override
    public String uploadMaterial(UploadMaterialDto umd) {
        String time = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        materialRepository.save(new MaterialEntity(umd.getLocation(),umd.getLabId(),umd.getUploader(),umd.getName(),time));
        return "资料上传成功";
    }

    @Override
    public String deleteMaterial(Integer id) {
        materialRepository.deleteById(id);
        return "资料删除成功";
    }

    @Override
    public Map<String,Object> getAllMaterials(Integer pageNum, Integer pageSize) {
        Page<MaterialEntity> page = materialRepository.findAll(PageRequest.of(pageNum-1,pageSize));
        List<String> nameList = new ArrayList<>();
        Map<String,Object> map = new HashMap<>();
        if(page.getContent().isEmpty())
        {
            return null;
        }
        map.put("materials",page.getContent());
        for (MaterialEntity material:page.getContent()
             ) {
            nameList.add(teacherRepository.findById(material.getUploader()).get().getName());
        }
        map.put("names",nameList);
        return map;
    }

    @Override
    public List<MaterialEntity> getAllByLabId(Integer labId) {
        return materialRepository.findAllByLabId(labId);
    }

    @Override
    public Map<String, Object> getAllByLabId(Integer labId, Integer pageNum, Integer pageSize) {
        Page<MaterialEntity> page = materialRepository.findAllByLabId(labId,PageRequest.of(pageNum-1,pageSize));
        List<String> nameList = new ArrayList<>();
        Map<String,Object> map = new HashMap<>();
        if(page.getContent().isEmpty())
        {
            return null;
        }
        map.put("materials",page.getContent());
        for (MaterialEntity material:page.getContent()
        ) {
            nameList.add(teacherRepository.findById(material.getUploader()).get().getName());
        }
        map.put("names",nameList);
        return map;
    }

    @Override
    public Page<MaterialEntity> getAllByUploader(String uploader, Integer pageNum, Integer pageSize) {
        return materialRepository.findAllByUploader(uploader,PageRequest.of(pageNum-1,pageSize));
    }

    @Override
    public List<MaterialEntity> getLatestFive() {
        List<MaterialEntity> all = materialRepository.findAll(Sort.by(Sort.Direction.DESC,"uploadTime"));
        if(all.size() < 5)
        {
            return all;
        }
        return all.subList(0,5);
    }
}
