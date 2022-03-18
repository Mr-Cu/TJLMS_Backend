package edu.tongji.tjlms.service.check;

import edu.tongji.tjlms.dto.StuGetCheckDto;
import edu.tongji.tjlms.dto.PostCheckDto;
import edu.tongji.tjlms.dto.TeacherGetCheckDto;

import java.util.List;

public interface CheckService {
    String postCheck(PostCheckDto pcd);
    List<StuGetCheckDto> getAllCheckByStuId(String stuId);

    String submitCheck(String stuId,Integer checkId);

    Double calculateAttendance(String stuId);

    List<TeacherGetCheckDto> getAllCheckByClassId(String classId);
}
