package edu.tongji.tjlms.service.findbackpwd;

import edu.tongji.tjlms.dto.FindBackPwdDto;
import edu.tongji.tjlms.dto.SafePwdDto;
import edu.tongji.tjlms.repository.AdminRepository;
import edu.tongji.tjlms.repository.StudentRepository;
import edu.tongji.tjlms.repository.TeacherRepository;
import edu.tongji.tjlms.util.EncryptSha256Util;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class FindBackPwdServiceImpl implements FindBackPwdService {
    @Resource
    StudentRepository studentRepository;
    @Resource
    TeacherRepository teacherRepository;
    @Resource
    AdminRepository adminRepository;

    @Override
    public String resetPwd(FindBackPwdDto fbpd) {
        int type = fbpd.getUserType();
        switch (type)
        {

            case 1:
            {
                studentRepository.updatePwd(fbpd.getId(),EncryptSha256Util.getSha256Str(fbpd.getNewPwd()));
                break;
            }
            case 2:
            {
                teacherRepository.updatePwd(fbpd.getId(),EncryptSha256Util.getSha256Str(fbpd.getNewPwd()));
                break;
            }
            default:
            {
                return "错误用户类型";
            }
        }
        return "密码修改成功";
    }

    @Override
    public String safePwd(SafePwdDto spd) {
        adminRepository.safePwd(spd.getId(), EncryptSha256Util.getSha256Str(spd.getPassword()));
        return "初始密码修改成功";
    }
}
