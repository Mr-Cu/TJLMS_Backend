package edu.tongji.tjlms.service.email;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import edu.tongji.tjlms.dto.EmailDto;
import edu.tongji.tjlms.model.StudentEntity;
import edu.tongji.tjlms.repository.StudentRepository;
import edu.tongji.tjlms.repository.TeacherRepository;

import java.util.Optional;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {EmailSendServiceImpl.class})
@ExtendWith(SpringExtension.class)
class EmailSendServiceImplTest {
    @Autowired
    private EmailSendServiceImpl emailSendServiceImpl;

    @MockBean
    private JavaMailSender javaMailSender;

    @MockBean
    private StudentRepository studentRepository;

    @MockBean(name = "TeacherRepository")
    private TeacherRepository teacherRepository;

    /**
     * Method under test: {@link EmailSendServiceImpl#sendEmail(EmailDto)}
     */
    @Test
    void testSendEmail() throws MailException {
        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setEmailAddr("42 Main St");
        studentEntity.setId("42");
        studentEntity.setName("Name");
        studentEntity.setPassword("iloveyou");
        studentEntity.setVerified(true);
        Optional<StudentEntity> ofResult = Optional.of(studentEntity);
        when(this.studentRepository.findById((String) any())).thenReturn(ofResult);
        doNothing().when(this.javaMailSender).send((org.springframework.mail.SimpleMailMessage) any());

        EmailDto emailDto = new EmailDto();
        emailDto.setEmailAddress("42 Main St");
        emailDto.setId("42");
        emailDto.setUserType(1);
        assertEquals("发送成功", this.emailSendServiceImpl.sendEmail(emailDto));
        verify(this.studentRepository).findById((String) any());
        verify(this.javaMailSender).send((org.springframework.mail.SimpleMailMessage) any());
    }

    /**
     * Method under test: {@link EmailSendServiceImpl#sendEmail(EmailDto)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testSendEmail2() throws MailException {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at edu.tongji.tjlms.service.email.EmailSendServiceImpl.sendEmail(EmailSendServiceImpl.java:63)
        //   In order to prevent sendEmail(EmailDto)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   sendEmail(EmailDto).
        //   See https://diff.blue/R013 to resolve this issue.

        when(this.studentRepository.findById((String) any())).thenReturn(null);
        doNothing().when(this.javaMailSender).send((org.springframework.mail.SimpleMailMessage) any());

        EmailDto emailDto = new EmailDto();
        emailDto.setEmailAddress("42 Main St");
        emailDto.setId("42");
        emailDto.setUserType(1);
        this.emailSendServiceImpl.sendEmail(emailDto);
    }

    /**
     * Method under test: {@link EmailSendServiceImpl#sendEmail(EmailDto)}
     */
    @Test
    void testSendEmail3() throws MailException {
        when(this.studentRepository.findById((String) any())).thenReturn(Optional.empty());
        doNothing().when(this.javaMailSender).send((org.springframework.mail.SimpleMailMessage) any());

        EmailDto emailDto = new EmailDto();
        emailDto.setEmailAddress("42 Main St");
        emailDto.setId("42");
        emailDto.setUserType(1);
        assertEquals("学工号错误", this.emailSendServiceImpl.sendEmail(emailDto));
        verify(this.studentRepository).findById((String) any());
    }

    /**
     * Method under test: {@link EmailSendServiceImpl#sendEmail(EmailDto)}
     */
    @Test
    void testSendEmail4() throws MailException {
        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setEmailAddr("42 Main St");
        studentEntity.setId("42");
        studentEntity.setName("Name");
        studentEntity.setPassword("iloveyou");
        studentEntity.setVerified(true);
        Optional<StudentEntity> ofResult = Optional.of(studentEntity);
        when(this.studentRepository.findById((String) any())).thenReturn(ofResult);
        doNothing().when(this.javaMailSender).send((org.springframework.mail.SimpleMailMessage) any());
        EmailDto emailDto = mock(EmailDto.class);
        when(emailDto.getUserType()).thenReturn(-1);
        when(emailDto.getId()).thenReturn("42");
        doNothing().when(emailDto).setEmailAddress((String) any());
        doNothing().when(emailDto).setId((String) any());
        doNothing().when(emailDto).setUserType((Integer) any());
        emailDto.setEmailAddress("42 Main St");
        emailDto.setId("42");
        emailDto.setUserType(1);
        assertEquals("错误用户类型", this.emailSendServiceImpl.sendEmail(emailDto));
        verify(emailDto).getUserType();
        verify(emailDto).getId();
        verify(emailDto).setEmailAddress((String) any());
        verify(emailDto).setId((String) any());
        verify(emailDto).setUserType((Integer) any());
    }

    /**
     * Method under test: {@link EmailSendServiceImpl#sendEmail(EmailDto)}
     */
    @Test
    void testSendEmail5() throws MailException {
        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setEmailAddr("42 Main St");
        studentEntity.setId("42");
        studentEntity.setName("Name");
        studentEntity.setPassword("iloveyou");
        studentEntity.setVerified(true);
        Optional<StudentEntity> ofResult = Optional.of(studentEntity);
        when(this.studentRepository.findById((String) any())).thenReturn(ofResult);
        doNothing().when(this.javaMailSender).send((org.springframework.mail.SimpleMailMessage) any());
        EmailDto emailDto = mock(EmailDto.class);
        when(emailDto.getUserType()).thenReturn(-1);
        when(emailDto.getId()).thenReturn(null);
        doNothing().when(emailDto).setEmailAddress((String) any());
        doNothing().when(emailDto).setId((String) any());
        doNothing().when(emailDto).setUserType((Integer) any());
        emailDto.setEmailAddress("42 Main St");
        emailDto.setId("42");
        emailDto.setUserType(1);
        assertEquals("学工号不能为空", this.emailSendServiceImpl.sendEmail(emailDto));
        verify(emailDto).getId();
        verify(emailDto).setEmailAddress((String) any());
        verify(emailDto).setId((String) any());
        verify(emailDto).setUserType((Integer) any());
    }

    /**
     * Method under test: default or parameterless constructor of {@link EmailSendServiceImpl}
     */
    @Test
    void testConstructor() {
        EmailSendServiceImpl actualEmailSendServiceImpl = new EmailSendServiceImpl();
        assertNull(actualEmailSendServiceImpl.studentRepository);
        assertNull(actualEmailSendServiceImpl.teacherRepository);
    }
}

