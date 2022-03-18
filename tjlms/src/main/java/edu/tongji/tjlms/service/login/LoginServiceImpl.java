package edu.tongji.tjlms.service.login;

import edu.tongji.tjlms.dto.LoginDto;
import edu.tongji.tjlms.model.AdminEntity;
import edu.tongji.tjlms.model.StudentEntity;
import edu.tongji.tjlms.model.TeacherEntity;
import edu.tongji.tjlms.repository.AdminRepository;
import edu.tongji.tjlms.repository.StudentRepository;
import edu.tongji.tjlms.repository.TeacherRepository;
import edu.tongji.tjlms.util.EncryptSha256Util;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * @author Charles Gao
 * @date 2021/10/20
 * @description the login service implementation
 */
@Service
public class LoginServiceImpl implements LoginService{
    @Resource
    AdminRepository adminRepository;
    @Resource
    StudentRepository studentRepository;
    @Resource
    TeacherRepository teacherRepository;

    @Override
    public Object login(LoginDto ld) {
        int userType = ld.getUserType();
        String email = ld.getEmailAddress();
        String password = EncryptSha256Util.getSha256Str(ld.getPassword());
        switch(userType)
        {
            // Admin
            case 0:
            {
                // JPA code
                Optional<AdminEntity> admin = adminRepository.findByEmailAddrAndPassword(email, password);
                if(!admin.isPresent())
                {
                    return "邮箱或密码错误";
                }
                admin.get().setPassword(null);
                return admin.get();
            }

            // Student
            case 1:
            {
                // JPA code
                Optional<StudentEntity> student = studentRepository.findByEmailAddrAndPassword(email,password);
                if(!student.isPresent())
                {
                    return "邮箱或密码错误";
                }
                if(!student.get().getVerified())
                {
                    return "尚未激活";
                }
                student.get().setPassword(null);
                return student.get();

            }

            // Teacher
            case 2:
            {
                // JPA code
                Optional<TeacherEntity> teacher = teacherRepository.findByEmailAddrAndPassword(email, password);
                if(!teacher.isPresent())
                {
                    return "邮箱或密码错误";
                }
                if(!teacher.get().getVerified())
                {
                    return "尚未激活";
                }
                teacher.get().setPassword(null);
                return teacher.get();

            }
            default:
            {
                return "错误用户类型";
            }
        }
    }
}
