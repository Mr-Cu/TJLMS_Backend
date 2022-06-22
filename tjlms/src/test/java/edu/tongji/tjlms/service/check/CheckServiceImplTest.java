package edu.tongji.tjlms.service.check;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import edu.tongji.tjlms.dto.PostCheckDto;
import edu.tongji.tjlms.dto.StuGetCheckDto;
import edu.tongji.tjlms.dto.TeacherGetCheckDto;
import edu.tongji.tjlms.model.CheckEntity;
import edu.tongji.tjlms.model.ClassEntity;
import edu.tongji.tjlms.model.StuCheckEntity;
import edu.tongji.tjlms.model.TakesEntity;
import edu.tongji.tjlms.repository.CheckRepository;
import edu.tongji.tjlms.repository.ClassRepository;
import edu.tongji.tjlms.repository.StuCheckRepository;
import edu.tongji.tjlms.repository.TakesRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {CheckServiceImpl.class})
@ExtendWith(SpringExtension.class)
class CheckServiceImplTest {
    @MockBean
    private CheckRepository checkRepository;

    @Autowired
    private CheckServiceImpl checkServiceImpl;

    @MockBean
    private ClassRepository classRepository;

    @MockBean
    private StuCheckRepository stuCheckRepository;

    @MockBean
    private TakesRepository takesRepository;

    /**
     * Method under test: {@link CheckServiceImpl#postCheck(PostCheckDto)}
     */
    @Test
    void testPostCheck1() {
        when(this.takesRepository.findAllByClassId((String) any())).thenReturn(new ArrayList<>());

        ClassEntity classEntity = new ClassEntity();
        classEntity.setAssistId("42");
        classEntity.setId("42");
        classEntity.setRespId("42");
        classEntity.setStuNum(10);
        classEntity.setTeacherId("42");
        when(this.classRepository.findAllById((String) any())).thenReturn(classEntity);

        CheckEntity checkEntity = new CheckEntity();
        checkEntity.setCheckedStudent(1);
        checkEntity.setClassId("42");
        checkEntity.setEndTime("End Time");
        checkEntity.setId(1);
        checkEntity.setStartTime("Start Time");
        when(this.checkRepository.saveAndFlush((CheckEntity) any())).thenReturn(checkEntity);

        PostCheckDto postCheckDto = new PostCheckDto();
        postCheckDto.setClassId("42");
        postCheckDto.setEnd("End");
        postCheckDto.setStart("Start");
        assertEquals("考勤发布成功", this.checkServiceImpl.postCheck(postCheckDto));
        verify(this.takesRepository).findAllByClassId((String) any());
        verify(this.classRepository).findAllById((String) any());
        verify(this.checkRepository).saveAndFlush((CheckEntity) any());
    }

    /**
     * Method under test: {@link CheckServiceImpl#postCheck(PostCheckDto)}
     */
    @Test
    void testPostCheck2() {
        TakesEntity takesEntity = new TakesEntity();
        takesEntity.setClassId("42");
        takesEntity.setStuId("42");
        takesEntity.setStuName("考勤发布成功");

        ArrayList<TakesEntity> takesEntityList = new ArrayList<>();
        takesEntityList.add(takesEntity);
        when(this.takesRepository.findAllByClassId((String) any())).thenReturn(takesEntityList);

        StuCheckEntity stuCheckEntity = new StuCheckEntity();
        stuCheckEntity.setCheckId(123);
        stuCheckEntity.setStuId("42");
        stuCheckEntity.setTime("Time");
        when(this.stuCheckRepository.save((StuCheckEntity) any())).thenReturn(stuCheckEntity);

        ClassEntity classEntity = new ClassEntity();
        classEntity.setAssistId("42");
        classEntity.setId("42");
        classEntity.setRespId("42");
        classEntity.setStuNum(10);
        classEntity.setTeacherId("42");
        when(this.classRepository.findAllById((String) any())).thenReturn(classEntity);

        CheckEntity checkEntity = new CheckEntity();
        checkEntity.setCheckedStudent(1);
        checkEntity.setClassId("42");
        checkEntity.setEndTime("End Time");
        checkEntity.setId(1);
        checkEntity.setStartTime("Start Time");
        when(this.checkRepository.saveAndFlush((CheckEntity) any())).thenReturn(checkEntity);

        PostCheckDto postCheckDto = new PostCheckDto();
        postCheckDto.setClassId("42");
        postCheckDto.setEnd("End");
        postCheckDto.setStart("Start");
        assertEquals("考勤发布成功", this.checkServiceImpl.postCheck(postCheckDto));
        verify(this.takesRepository).findAllByClassId((String) any());
        verify(this.stuCheckRepository).save((StuCheckEntity) any());
        verify(this.classRepository).findAllById((String) any());
        verify(this.checkRepository).saveAndFlush((CheckEntity) any());
    }

    /**
     * Method under test: {@link CheckServiceImpl#getAllCheckByStuId(String)}
     */
    @Test
    void testGetAllCheckByStuId1() {
        TakesEntity takesEntity = new TakesEntity();
        takesEntity.setClassId("42");
        takesEntity.setStuId("42");
        takesEntity.setStuName("Stu Name");
        when(this.takesRepository.findByStuId((String) any())).thenReturn(takesEntity);
        when(this.checkRepository.findAllByClassId((String) any())).thenReturn(new ArrayList<>());
        assertNull(this.checkServiceImpl.getAllCheckByStuId("42"));
        verify(this.takesRepository).findByStuId((String) any());
        verify(this.checkRepository).findAllByClassId((String) any());
    }

    /**
     * Method under test: {@link CheckServiceImpl#getAllCheckByStuId(String)}
     */
    @Test
    void testGetAllCheckByStuId2() {
        TakesEntity takesEntity = new TakesEntity();
        takesEntity.setClassId("42");
        takesEntity.setStuId("42");
        takesEntity.setStuName("Stu Name");
        when(this.takesRepository.findByStuId((String) any())).thenReturn(takesEntity);

        StuCheckEntity stuCheckEntity = new StuCheckEntity();
        stuCheckEntity.setCheckId(123);
        stuCheckEntity.setStuId("42");
        stuCheckEntity.setTime("Time");
        when(this.stuCheckRepository.findByStuIdAndCheckId((String) any(), (Integer) any())).thenReturn(stuCheckEntity);

        CheckEntity checkEntity = new CheckEntity();
        checkEntity.setCheckedStudent(1);
        checkEntity.setClassId("42");
        checkEntity.setEndTime("End Time");
        checkEntity.setId(1);
        checkEntity.setStartTime("Start Time");

        ArrayList<CheckEntity> checkEntityList = new ArrayList<>();
        checkEntityList.add(checkEntity);
        when(this.checkRepository.findAllByClassId((String) any())).thenReturn(checkEntityList);
        List<StuGetCheckDto> actualAllCheckByStuId = this.checkServiceImpl.getAllCheckByStuId("42");
        assertEquals(1, actualAllCheckByStuId.size());
        StuGetCheckDto getResult = actualAllCheckByStuId.get(0);
        assertEquals("End Time", getResult.getEndTime());
        assertTrue(getResult.isHasChecked());
        assertEquals("Start Time", getResult.getStartTime());
        assertEquals(1, getResult.getId());
        verify(this.takesRepository).findByStuId((String) any());
        verify(this.stuCheckRepository).findByStuIdAndCheckId((String) any(), (Integer) any());
        verify(this.checkRepository).findAllByClassId((String) any());
    }

    /**
     * Method under test: {@link CheckServiceImpl#getAllCheckByStuId(String)}
     */
    @Test
    void testGetAllCheckByStuId3() {
        TakesEntity takesEntity = new TakesEntity();
        takesEntity.setClassId("42");
        takesEntity.setStuId("42");
        takesEntity.setStuName("Stu Name");
        when(this.takesRepository.findByStuId((String) any())).thenReturn(takesEntity);

        StuCheckEntity stuCheckEntity = new StuCheckEntity();
        stuCheckEntity.setCheckId(123);
        stuCheckEntity.setStuId("42");
        stuCheckEntity.setTime(null);
        when(this.stuCheckRepository.findByStuIdAndCheckId((String) any(), (Integer) any())).thenReturn(stuCheckEntity);

        CheckEntity checkEntity = new CheckEntity();
        checkEntity.setCheckedStudent(1);
        checkEntity.setClassId("42");
        checkEntity.setEndTime("End Time");
        checkEntity.setId(1);
        checkEntity.setStartTime("Start Time");

        ArrayList<CheckEntity> checkEntityList = new ArrayList<>();
        checkEntityList.add(checkEntity);
        when(this.checkRepository.findAllByClassId((String) any())).thenReturn(checkEntityList);
        List<StuGetCheckDto> actualAllCheckByStuId = this.checkServiceImpl.getAllCheckByStuId("42");
        assertEquals(1, actualAllCheckByStuId.size());
        StuGetCheckDto getResult = actualAllCheckByStuId.get(0);
        assertEquals("End Time", getResult.getEndTime());
        assertFalse(getResult.isHasChecked());
        assertEquals("Start Time", getResult.getStartTime());
        assertEquals(1, getResult.getId());
        verify(this.takesRepository).findByStuId((String) any());
        verify(this.stuCheckRepository).findByStuIdAndCheckId((String) any(), (Integer) any());
        verify(this.checkRepository).findAllByClassId((String) any());
    }

    /**
     * Method under test: {@link CheckServiceImpl#submitCheck(String, Integer)}
     */
    @Test
    void testSubmitCheck1() {
        CheckEntity checkEntity = new CheckEntity();
        checkEntity.setCheckedStudent(1);
        checkEntity.setClassId("42");
        checkEntity.setEndTime("End Time");
        checkEntity.setId(1);
        checkEntity.setStartTime("Start Time");
        Optional<CheckEntity> ofResult = Optional.of(checkEntity);
        when(this.checkRepository.findById((Integer) any())).thenReturn(ofResult);
        assertEquals("时间转换失败", this.checkServiceImpl.submitCheck("42", 123));
        verify(this.checkRepository).findById((Integer) any());
    }

    /**
     * Method under test: {@link CheckServiceImpl#submitCheck(String, Integer)}
     */
    @Test
    void testSubmitCheck2() {
        when(this.checkRepository.findById((Integer) any())).thenReturn(Optional.empty());
        CheckEntity checkEntity = mock(CheckEntity.class);
        when(checkEntity.getEndTime()).thenReturn("End Time");
        when(checkEntity.getStartTime()).thenReturn("Start Time");
        doNothing().when(checkEntity).setCheckedStudent((Integer) any());
        doNothing().when(checkEntity).setClassId((String) any());
        doNothing().when(checkEntity).setEndTime((String) any());
        doNothing().when(checkEntity).setId(anyInt());
        doNothing().when(checkEntity).setStartTime((String) any());
        checkEntity.setCheckedStudent(1);
        checkEntity.setClassId("42");
        checkEntity.setEndTime("End Time");
        checkEntity.setId(1);
        checkEntity.setStartTime("Start Time");
        assertEquals("学生编号不能为空", this.checkServiceImpl.submitCheck(null, 123));
        verify(checkEntity).setCheckedStudent((Integer) any());
        verify(checkEntity).setClassId((String) any());
        verify(checkEntity).setEndTime((String) any());
        verify(checkEntity).setId(anyInt());
        verify(checkEntity).setStartTime((String) any());
    }

    /**
     * Method under test: {@link CheckServiceImpl#submitCheck(String, Integer)}
     */
    @Test
    void testSubmitCheck3() {
        when(this.checkRepository.findById((Integer) any())).thenReturn(Optional.empty());
        CheckEntity checkEntity = mock(CheckEntity.class);
        when(checkEntity.getEndTime()).thenReturn("End Time");
        when(checkEntity.getStartTime()).thenReturn("Start Time");
        doNothing().when(checkEntity).setCheckedStudent((Integer) any());
        doNothing().when(checkEntity).setClassId((String) any());
        doNothing().when(checkEntity).setEndTime((String) any());
        doNothing().when(checkEntity).setId(anyInt());
        doNothing().when(checkEntity).setStartTime((String) any());
        checkEntity.setCheckedStudent(1);
        checkEntity.setClassId("42");
        checkEntity.setEndTime("End Time");
        checkEntity.setId(1);
        checkEntity.setStartTime("Start Time");
        assertEquals("签到编号不能为空", this.checkServiceImpl.submitCheck("42", null));
        verify(checkEntity).setCheckedStudent((Integer) any());
        verify(checkEntity).setClassId((String) any());
        verify(checkEntity).setEndTime((String) any());
        verify(checkEntity).setId(anyInt());
        verify(checkEntity).setStartTime((String) any());
    }

    /**
     * Method under test: {@link CheckServiceImpl#getAllCheckByClassId(String)}
     */
    @Test
    void testGetAllCheckByClassId1() {
        when(this.takesRepository.findAllByClassId((String) any())).thenReturn(new ArrayList<>());
        when(this.checkRepository.findAllByClassId((String) any())).thenReturn(new ArrayList<>());
        assertNull(this.checkServiceImpl.getAllCheckByClassId("42"));
        verify(this.takesRepository).findAllByClassId((String) any());
        verify(this.checkRepository).findAllByClassId((String) any());
    }

    /**
     * Method under test: {@link CheckServiceImpl#getAllCheckByClassId(String)}
     */
    @Test
    void testGetAllCheckByClassId2() {
        when(this.takesRepository.findAllByClassId((String) any())).thenReturn(new ArrayList<>());
        when(this.stuCheckRepository.findAllByCheckId((Integer) any())).thenReturn(new ArrayList<>());

        CheckEntity checkEntity = new CheckEntity();
        checkEntity.setCheckedStudent(1);
        checkEntity.setClassId("42");
        checkEntity.setEndTime("End Time");
        checkEntity.setId(1);
        checkEntity.setStartTime("Start Time");

        ArrayList<CheckEntity> checkEntityList = new ArrayList<>();
        checkEntityList.add(checkEntity);
        when(this.checkRepository.findAllByClassId((String) any())).thenReturn(checkEntityList);
        List<TeacherGetCheckDto> actualAllCheckByClassId = this.checkServiceImpl.getAllCheckByClassId("42");
        assertEquals(1, actualAllCheckByClassId.size());
        TeacherGetCheckDto getResult = actualAllCheckByClassId.get(0);
        assertEquals(0, getResult.getAll());
        assertEquals("Start Time", getResult.getStartTime());
        assertEquals(1, getResult.getId());
        assertEquals("End Time", getResult.getEndTime());
        assertEquals(0, getResult.getChecked());
        verify(this.takesRepository).findAllByClassId((String) any());
        verify(this.stuCheckRepository).findAllByCheckId((Integer) any());
        verify(this.checkRepository).findAllByClassId((String) any());
    }

    /**
     * Method under test: {@link CheckServiceImpl#getAllCheckByClassId(String)}
     */
    @Test
    void testGetAllCheckByClassId3() {
        when(this.takesRepository.findAllByClassId((String) any())).thenReturn(new ArrayList<>());

        StuCheckEntity stuCheckEntity = new StuCheckEntity();
        stuCheckEntity.setCheckId(123);
        stuCheckEntity.setStuId("42");
        stuCheckEntity.setTime("Time");

        ArrayList<StuCheckEntity> stuCheckEntityList = new ArrayList<>();
        stuCheckEntityList.add(stuCheckEntity);
        when(this.stuCheckRepository.findAllByCheckId((Integer) any())).thenReturn(stuCheckEntityList);

        CheckEntity checkEntity = new CheckEntity();
        checkEntity.setCheckedStudent(1);
        checkEntity.setClassId("42");
        checkEntity.setEndTime("End Time");
        checkEntity.setId(1);
        checkEntity.setStartTime("Start Time");

        ArrayList<CheckEntity> checkEntityList = new ArrayList<>();
        checkEntityList.add(checkEntity);
        when(this.checkRepository.findAllByClassId((String) any())).thenReturn(checkEntityList);
        List<TeacherGetCheckDto> actualAllCheckByClassId = this.checkServiceImpl.getAllCheckByClassId("42");
        assertEquals(1, actualAllCheckByClassId.size());
        TeacherGetCheckDto getResult = actualAllCheckByClassId.get(0);
        assertEquals(0, getResult.getAll());
        assertEquals("Start Time", getResult.getStartTime());
        assertEquals(1, getResult.getId());
        assertEquals("End Time", getResult.getEndTime());
        assertEquals(1, getResult.getChecked());
        verify(this.takesRepository).findAllByClassId((String) any());
        verify(this.stuCheckRepository).findAllByCheckId((Integer) any());
        verify(this.checkRepository).findAllByClassId((String) any());
    }
}

