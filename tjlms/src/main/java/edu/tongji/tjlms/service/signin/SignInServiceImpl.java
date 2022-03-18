package edu.tongji.tjlms.service.signin;

import edu.tongji.tjlms.dto.SignInDto;
import edu.tongji.tjlms.repository.StudentRepository;
import edu.tongji.tjlms.repository.TeacherRepository;
import edu.tongji.tjlms.util.EncryptSha256Util;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Charles Gao
 * @date 2021/10/20
 * @description the sign in service implementation
 */
@Service
public class SignInServiceImpl implements SignInService{

    @Resource
    StudentRepository studentRepository;
    @Resource
    TeacherRepository teacherRepository;

    @Override
    public String signIn(SignInDto sid) {

        int userType = sid.getUserType();
        String id = sid.getId();
        String email = sid.getEmailAddress();
        // encrypt the password
        String password = EncryptSha256Util.getSha256Str(sid.getPassword());
        // JPA code
        int res;
        switch (userType)
        {
            case 1:
            {
                res = studentRepository.verify(email,password,id);
                if(res == 0)
                {
                    return "注册失败";
                }
                break;

            }
            case 2:
            {
                res = teacherRepository.verify(email,password,id);
                if(res == 0)
                {
                   return "注册失败";
                }
                break;
            }
        }
        return "注册成功";
    }
}
