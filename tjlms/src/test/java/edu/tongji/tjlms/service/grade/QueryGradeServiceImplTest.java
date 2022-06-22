package edu.tongji.tjlms.service.grade;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import edu.tongji.tjlms.dto.FinalGradeDto;
import edu.tongji.tjlms.dto.QueryGradeDto;
import edu.tongji.tjlms.model.CourseEntity;
import edu.tongji.tjlms.model.QueryGradeEntity;
import edu.tongji.tjlms.repository.CourseRepository;
import edu.tongji.tjlms.repository.LabRepository;
import edu.tongji.tjlms.repository.QueryGradeRepository;
import edu.tongji.tjlms.repository.ReportRepository;
import edu.tongji.tjlms.repository.SummatorBasicRepository;
import edu.tongji.tjlms.service.check.CheckService;

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

@ContextConfiguration(classes = {QueryGradeServiceImpl.class})
@ExtendWith(SpringExtension.class)
class QueryGradeServiceImplTest {
    @MockBean
    private CheckService checkService;

    @MockBean
    private CourseRepository courseRepository;

    @MockBean
    private LabRepository labRepository;

    @MockBean
    private QueryGradeRepository queryGradeRepository;

    @Autowired
    private QueryGradeServiceImpl queryGradeServiceImpl;

    @MockBean
    private ReportRepository reportRepository;

    @MockBean
    private SummatorBasicRepository summatorBasicRepository;

    /**
     * Method under test: {@link QueryGradeServiceImpl#queryFinalGrade(String)}
     */
    @Test
    void testQueryFinalGrade() {
        when(this.queryGradeRepository.findAllByStuId((String) any())).thenReturn(new ArrayList<>());

        CourseEntity courseEntity = new CourseEntity();
        courseEntity.setCredit(10.0d);
        courseEntity.setId("42");
        courseEntity.setIntro("Intro");
        courseEntity.setName("Name");
        courseEntity.setPeriods(1);
        courseEntity.setRatio(10.0d);
        Optional<CourseEntity> ofResult = Optional.of(courseEntity);
        when(this.courseRepository.findById((String) any())).thenReturn(ofResult);
        when(this.checkService.calculateAttendance((String) any())).thenReturn(10.0d);
        assertNull(this.queryGradeServiceImpl.queryFinalGrade("42"));
        verify(this.queryGradeRepository).findAllByStuId((String) any());
        verify(this.courseRepository).findById((String) any());
        verify(this.checkService).calculateAttendance((String) any());
    }

    /**
     * Method under test: {@link QueryGradeServiceImpl#queryFinalGrade(String)}
     */
    @Test
    void testQueryFinalGrade2() {
        QueryGradeEntity queryGradeEntity = new QueryGradeEntity();
        queryGradeEntity.setLabId(123);
        queryGradeEntity.setName("420000");
        queryGradeEntity.setNote("420000");
        queryGradeEntity.setScore(1.0d);
        queryGradeEntity.setStuId("42");
        queryGradeEntity.setUpdateDate("2020-03-01");

        ArrayList<QueryGradeEntity> queryGradeEntityList = new ArrayList<>();
        queryGradeEntityList.add(queryGradeEntity);
        when(this.queryGradeRepository.findAllByStuId((String) any())).thenReturn(queryGradeEntityList);

        CourseEntity courseEntity = new CourseEntity();
        courseEntity.setCredit(10.0d);
        courseEntity.setId("42");
        courseEntity.setIntro("Intro");
        courseEntity.setName("Name");
        courseEntity.setPeriods(1);
        courseEntity.setRatio(10.0d);
        Optional<CourseEntity> ofResult = Optional.of(courseEntity);
        when(this.courseRepository.findById((String) any())).thenReturn(ofResult);
        when(this.checkService.calculateAttendance((String) any())).thenReturn(10.0d);
        FinalGradeDto actualQueryFinalGradeResult = this.queryGradeServiceImpl.queryFinalGrade("42");
        assertEquals(10.0d, actualQueryFinalGradeResult.getAttendance().doubleValue());
        assertEquals(91.0d, actualQueryFinalGradeResult.getFinalScore().doubleValue());
        assertEquals("优", actualQueryFinalGradeResult.getFinalGrade());
        List<QueryGradeDto> eachGrades = actualQueryFinalGradeResult.getEachGrades();
        assertEquals(1, eachGrades.size());
        QueryGradeDto getResult = eachGrades.get(0);
        assertEquals("不及格", getResult.getGrade());
        assertSame(queryGradeEntity, getResult.getQueryGradeEntity());
        verify(this.queryGradeRepository).findAllByStuId((String) any());
        verify(this.courseRepository).findById((String) any());
        verify(this.checkService).calculateAttendance((String) any());
    }

    /**
     * Method under test: {@link QueryGradeServiceImpl#queryFinalGrade(String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testQueryFinalGrade3() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.util.NoSuchElementException: No value present
        //       at java.util.Optional.get(Optional.java:135)
        //       at edu.tongji.tjlms.service.grade.QueryGradeServiceImpl.queryFinalGrade(QueryGradeServiceImpl.java:64)
        //   In order to prevent queryFinalGrade(String)
        //   from throwing NoSuchElementException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   queryFinalGrade(String).
        //   See https://diff.blue/R013 to resolve this issue.

        when(this.queryGradeRepository.findAllByStuId((String) any())).thenReturn(new ArrayList<>());
        when(this.courseRepository.findById((String) any())).thenReturn(Optional.empty());
        when(this.checkService.calculateAttendance((String) any())).thenReturn(10.0d);
        this.queryGradeServiceImpl.queryFinalGrade("42");
    }
}

