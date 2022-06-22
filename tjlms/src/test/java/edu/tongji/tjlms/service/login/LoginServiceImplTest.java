package edu.tongji.tjlms.service.login;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {LoginServiceImpl.class})
@ExtendWith(SpringExtension.class)
class LoginServiceImplTest {
    @MockBean(name = "AdminRepository")
    private AdminRepository adminRepository;

    @Autowired
    private LoginServiceImpl loginServiceImpl;

    @MockBean
    private StudentRepository studentRepository;

    @MockBean(name = "TeacherRepository")
    private TeacherRepository teacherRepository;

    /**
     * Method under test: {@link LoginServiceImpl#login(LoginDto)}
     */
    @Test
    void testLogin6() {
        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setEmailAddr("42 Main St");
        studentEntity.setId("42");
        studentEntity.setPassword("iloveyou");
        studentEntity.setVerified(true);
        Optional<StudentEntity> ofResult = Optional.of(studentEntity);
        when(this.studentRepository.findByEmailAddrAndPassword((String) any(), (String) any())).thenReturn(ofResult);

        LoginDto loginDto = new LoginDto();
        loginDto.setEmailAddress("42 Main St");
        loginDto.setPassword("iloveyou");
        loginDto.setUserType(1);
        assertEquals(loginDto.getEmailAddress(), ((LoginStudentDto) this.loginServiceImpl.login(loginDto)).getEmailAddr());
        assertEquals("iloveyou", loginDto.getPassword());
        assertEquals("42", ((LoginStudentDto) this.loginServiceImpl.login(loginDto)).getId());
    }

    /**
     * Method under test: {@link LoginServiceImpl#login(LoginDto)}
     */
    @Test
    void testLogin2() {
        when(this.studentRepository.findByEmailAddrAndPassword((String) any(), (String) any()))
                .thenReturn(Optional.empty());

        LoginDto loginDto = new LoginDto();
        loginDto.setEmailAddress("1534881591@qq.com");
        loginDto.setPassword("654321");
        loginDto.setUserType(1);
        assertEquals("邮箱或密码错误", this.loginServiceImpl.login(loginDto));

        verify(this.studentRepository).findByEmailAddrAndPassword((String) any(), (String) any());
    }

    /**
     * Method under test: {@link LoginServiceImpl#login(LoginDto)}
     */
    @Test
    void testLogin4_1() {
        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setEmailAddr("1030587461@qq.com");
        studentEntity.setId("42");
        studentEntity.setName("Name");
        studentEntity.setPassword("123456");
        studentEntity.setVerified(true);
        Optional<StudentEntity> ofResult = Optional.of(studentEntity);
        when(this.studentRepository.findByEmailAddrAndPassword((String) any(), (String) any())).thenReturn(ofResult);

        LoginDto loginDto = mock(LoginDto.class);
        when(loginDto.getUserType()).thenReturn(-1);
        when(loginDto.getEmailAddress()).thenReturn("42 Main St");
        when(loginDto.getPassword()).thenReturn("iloveyou");
        doNothing().when(loginDto).setEmailAddress((String) any());
        doNothing().when(loginDto).setPassword((String) any());
        doNothing().when(loginDto).setUserType((Integer) any());
        loginDto.setEmailAddress("42 Main St");
        loginDto.setPassword("iloveyou");
        loginDto.setUserType(-1);
        assertEquals("错误用户类型", this.loginServiceImpl.login(loginDto));

        verify(loginDto).getUserType();
        verify(loginDto).getEmailAddress();
        verify(loginDto).getPassword();
        verify(loginDto).setEmailAddress((String) any());
        verify(loginDto).setPassword((String) any());
        verify(loginDto).setUserType((Integer) any());
    }

    /**
     * Method under test: {@link LoginServiceImpl#login(LoginDto)}
     */
    @Test
    void testLogin4_2() {
        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setEmailAddr("1030587461@qq.com");
        studentEntity.setId("42");
        studentEntity.setName("Name");
        studentEntity.setPassword("123456");
        studentEntity.setVerified(true);
        Optional<StudentEntity> ofResult = Optional.of(studentEntity);
        when(this.studentRepository.findByEmailAddrAndPassword((String) any(), (String) any())).thenReturn(ofResult);

        LoginDto loginDto = mock(LoginDto.class);
        when(loginDto.getUserType()).thenReturn(3);
        when(loginDto.getEmailAddress()).thenReturn("42 Main St");
        when(loginDto.getPassword()).thenReturn("iloveyou");
        doNothing().when(loginDto).setEmailAddress((String) any());
        doNothing().when(loginDto).setPassword((String) any());
        doNothing().when(loginDto).setUserType((Integer) any());
        loginDto.setEmailAddress("42 Main St");
        loginDto.setPassword("iloveyou");
        loginDto.setUserType(3);
        assertEquals("错误用户类型", this.loginServiceImpl.login(loginDto));

        verify(loginDto).getUserType();
        verify(loginDto).getEmailAddress();
        verify(loginDto).getPassword();
        verify(loginDto).setEmailAddress((String) any());
        verify(loginDto).setPassword((String) any());
        verify(loginDto).setUserType((Integer) any());
    }

    /**
     * Method under test: {@link LoginServiceImpl#login(LoginDto)}
     */
    @Test
    void testLogin5() {
        AdminEntity adminEntity = new AdminEntity();
        adminEntity.setEmailAddr("42 Main St");
        adminEntity.setId("42");
        adminEntity.setName("Name");
        adminEntity.setPassword("iloveyou");
        adminEntity.setPwdReset(true);
        adminEntity.setTelNum("Tel Num");
        Optional<AdminEntity> ofResult1 = Optional.of(adminEntity);
        when(this.adminRepository.findByEmailAddrAndPassword((String) any(), (String) any())).thenReturn(ofResult1);

        LoginDto loginDto = mock(LoginDto.class);
        when(loginDto.getUserType()).thenReturn(0);
        when(loginDto.getEmailAddress()).thenReturn("1030587461@qq.com");
        when(loginDto.getPassword()).thenReturn("123456");
        doNothing().when(loginDto).setEmailAddress((String) any());
        doNothing().when(loginDto).setPassword((String) any());
        doNothing().when(loginDto).setUserType((Integer) any());
        loginDto.setEmailAddress("1030587461@qq.com");
        loginDto.setPassword("123456");
        loginDto.setUserType(0);
        assertEquals(adminEntity.getEmailAddr(), ((LoginAdminDto) this.loginServiceImpl.login(loginDto)).getEmailAddr());
        assertEquals(adminEntity.getTelNum(), ((LoginAdminDto) this.loginServiceImpl.login(loginDto)).getTelNum());
        assertTrue(((LoginAdminDto) this.loginServiceImpl.login(loginDto)).getPwdReset());
        assertEquals(adminEntity.getName(), ((LoginAdminDto) this.loginServiceImpl.login(loginDto)).getName());
        assertEquals(adminEntity.getId(), ((LoginAdminDto) this.loginServiceImpl.login(loginDto)).getId());
    }

    /**
     * Method under test: {@link LoginServiceImpl#login(LoginDto)}
     */
    @Test
    void testLogin1() {
        when(this.adminRepository.findByEmailAddrAndPassword((String) any(), (String) any())).thenReturn(Optional.empty());

        LoginDto loginDto = mock(LoginDto.class);
        when(loginDto.getUserType()).thenReturn(0);
        when(loginDto.getEmailAddress()).thenReturn("1030587461@qq.com");
        when(loginDto.getPassword()).thenReturn("654321");
        doNothing().when(loginDto).setEmailAddress((String) any());
        doNothing().when(loginDto).setPassword((String) any());
        doNothing().when(loginDto).setUserType((Integer) any());
        loginDto.setEmailAddress("1030587461@qq.com");
        loginDto.setPassword("654321");
        loginDto.setUserType(0);
        assertEquals("邮箱或密码错误", this.loginServiceImpl.login(loginDto));

        verify(this.adminRepository).findByEmailAddrAndPassword((String) any(), (String) any());
        verify(loginDto).getUserType();
        verify(loginDto).getEmailAddress();
        verify(loginDto).getPassword();
        verify(loginDto).setEmailAddress((String) any());
        verify(loginDto).setPassword((String) any());
        verify(loginDto).setUserType((Integer) any());
    }

    /**
     * Method under test: {@link LoginServiceImpl#login(LoginDto)}
     */
    @Test
    void testLogin7() {
        TeacherEntity teacherEntity = new TeacherEntity();
        teacherEntity.setEmailAddr("huangjie@tongji.edu.cn");
        teacherEntity.setGrade(true);
        teacherEntity.setId("42");
        teacherEntity.setName("Name");
        teacherEntity.setPassword("123456");
        teacherEntity.setReleaseLab(true);
        teacherEntity.setType(1);
        teacherEntity.setVerified(true);
        Optional<TeacherEntity> ofResult = Optional.of(teacherEntity);
        when(this.teacherRepository.findByEmailAddrAndPassword((String) any(), (String) any())).thenReturn(ofResult);

        LoginDto loginDto = mock(LoginDto.class);
        when(loginDto.getUserType()).thenReturn(2);
        when(loginDto.getEmailAddress()).thenReturn("huangjie@tongji.edu.cn");
        when(loginDto.getPassword()).thenReturn("123456");
        doNothing().when(loginDto).setEmailAddress((String) any());
        doNothing().when(loginDto).setPassword((String) any());
        doNothing().when(loginDto).setUserType((Integer) any());
        loginDto.setEmailAddress("huangjie@tongji.edu.cn");
        loginDto.setPassword("123456");
        loginDto.setUserType(2);
        assertEquals(teacherEntity.getPassword(),loginDto.getPassword());
        assertEquals(loginDto.getEmailAddress(), ((LoginTeacherDto) this.loginServiceImpl.login(loginDto)).getEmailAddr());
        assertTrue(((LoginTeacherDto) this.loginServiceImpl.login(loginDto)).getVerified());
        assertEquals(teacherEntity.getType(), ((LoginTeacherDto) this.loginServiceImpl.login(loginDto)).getType().intValue());
        assertTrue(((LoginTeacherDto) this.loginServiceImpl.login(loginDto)).getReleaseLab());
        assertEquals(teacherEntity.getName(), ((LoginTeacherDto) this.loginServiceImpl.login(loginDto)).getName());
        assertEquals(teacherEntity.getId(), ((LoginTeacherDto) this.loginServiceImpl.login(loginDto)).getId());
        assertTrue(((LoginTeacherDto) this.loginServiceImpl.login(loginDto)).getGrade());
    }

    /**
     * Method under test: {@link LoginServiceImpl#login(LoginDto)}
     */
    @Test
    void testLogin3() {
        when(this.teacherRepository.findByEmailAddrAndPassword((String) any(), (String) any()))
                .thenReturn(Optional.empty());

        LoginDto loginDto = mock(LoginDto.class);
        when(loginDto.getUserType()).thenReturn(2);
        when(loginDto.getEmailAddress()).thenReturn("huangjie@tongji.edu.cn");
        when(loginDto.getPassword()).thenReturn("654321");
        doNothing().when(loginDto).setEmailAddress((String) any());
        doNothing().when(loginDto).setPassword((String) any());
        doNothing().when(loginDto).setUserType((Integer) any());
        loginDto.setEmailAddress("huangjie@tongji.edu.cn");
        loginDto.setPassword("654321");
        loginDto.setUserType(2);
        assertEquals("邮箱或密码错误", this.loginServiceImpl.login(loginDto));

        verify(this.teacherRepository).findByEmailAddrAndPassword((String) any(), (String) any());
        verify(loginDto).getUserType();
        verify(loginDto).getEmailAddress();
        verify(loginDto).getPassword();
        verify(loginDto).setEmailAddress((String) any());
        verify(loginDto).setPassword((String) any());
        verify(loginDto).setUserType((Integer) any());
    }
}
