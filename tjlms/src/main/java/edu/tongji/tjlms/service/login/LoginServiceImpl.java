package edu.tongji.tjlms.service.login;

import edu.tongji.tjlms.dto.LoginAdminDto;
import edu.tongji.tjlms.dto.LoginDto;
import edu.tongji.tjlms.dto.LoginStudentDto;
import edu.tongji.tjlms.dto.LoginTeacherDto;
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
        switch(userType){
            // Admin
            case 0:
            {
                // JPA code
                Optional<AdminEntity> admin = adminRepository.findByEmailAddrAndPassword(email, password);
                if(!admin.isPresent()){
                    return "邮箱或密码错误";
                }
                LoginAdminDto loginAdmin=new LoginAdminDto();
                loginAdmin.setEmailAddr(admin.get().getEmailAddr());
                loginAdmin.setName(admin.get().getName());
                loginAdmin.setId(admin.get().getId());
                loginAdmin.setPwdReset(admin.get().getPwdReset());
                loginAdmin.setTelNum(admin.get().getTelNum());
                return loginAdmin;
            }
            // Student
            case 1:
            {
                // JPA code
                Optional<StudentEntity> student = studentRepository.findByEmailAddrAndPassword(email,password);
                if(!student.isPresent()){
                    return "邮箱或密码错误";
                }
                //不存在尚未激活的情况，因为只能通过邮箱登录，尚未注册就没有邮箱，而一旦注册就能激活账户
                LoginStudentDto loginStudent=new LoginStudentDto();
                loginStudent.setEmailAddr(student.get().getEmailAddr());
                loginStudent.setVerified(student.get().getVerified());
                loginStudent.setName(student.get().getName());
                loginStudent.setId(student.get().getId());
                return loginStudent;

            }
            // Teacher
            case 2:
            {
                // JPA code
                Optional<TeacherEntity> teacher = teacherRepository.findByEmailAddrAndPassword(email, password);
                if(!teacher.isPresent()){
                    return "邮箱或密码错误";
                }
                //不存在尚未激活的情况，因为只能通过邮箱登录，尚未注册就没有邮箱，而一旦注册就能激活账户
                LoginTeacherDto loginTeacher=new LoginTeacherDto();
                loginTeacher.setGrade(teacher.get().getGrade());
                loginTeacher.setType(teacher.get().getType());
                loginTeacher.setReleaseLab(teacher.get().getReleaseLab());
                loginTeacher.setName(teacher.get().getName());
                loginTeacher.setVerified(teacher.get().getVerified());
                loginTeacher.setId(teacher.get().getId());
                loginTeacher.setEmailAddr(teacher.get().getEmailAddr());
                return loginTeacher;
            }
            default:
            {
                return "错误用户类型";
            }
        }
    }
}
