package edu.tongji.tjlms.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.tongji.tjlms.dto.GradeDto;
import edu.tongji.tjlms.model.*;
import edu.tongji.tjlms.service.grade.GradeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.HashMap;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {GradeController.class})
@ExtendWith(SpringExtension.class)
class GradeControllerTest {
    @Autowired
    private GradeController gradeController;

    @MockBean
    private GradeService gradeService;

    /**
     * Method under test: {@link GradeController#getReportList(String)}
     */
    @Test
    void testGetReportList() throws Exception {
        when(this.gradeService.getReportList((String) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/get/report/list/{teacherId}", "42");
        MockMvcBuilders.standaloneSetup(this.gradeController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link GradeController#getReportList(String)}
     */
    @Test
    void testGetReportList2() throws Exception {
        when(this.gradeService.getReportList((String) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/get/report/list/{teacherId}", "42");
        getResult.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(this.gradeController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link GradeController#getIndividualGrade(String, Integer)}
     */
    @Test
    void testGetIndividualGrade() throws Exception {
        LabGradeEntity labGradeEntity = new LabGradeEntity();
        labGradeEntity.setClassId("42");
        labGradeEntity.setLabId(123);
        labGradeEntity.setNote("Note");
        labGradeEntity.setScore(10.0d);
        labGradeEntity.setStuId("42");
        labGradeEntity.setTeacherId("42");
        labGradeEntity.setUpdateDate("2020-03-01");
        labGradeEntity.setVisible(true);
        when(this.gradeService.getParticularGrade((String) any(), (Integer) any())).thenReturn(labGradeEntity);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/get/grade/individual");
        MockHttpServletRequestBuilder requestBuilder = getResult.param("labId", String.valueOf(1)).param("stuId", "foo");
        MockMvcBuilders.standaloneSetup(this.gradeController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"stuId\":\"42\",\"classId\":\"42\",\"updateDate\":\"2020-03-01\",\"visible\":true,\"labId\":123,\"score\":10.0,\"note"
                                        + "\":\"Note\",\"teacherId\":\"42\"}"));
    }

    /**
     * Method under test: {@link GradeController#getMyClasses(String)}
     */
    @Test
    void testGetMyClasses() throws Exception {
        when(this.gradeService.getMyClasses((String) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/get/my/classes")
                .param("teacherId", "foo");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.gradeController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("????????"));
    }

    /**
     * Method under test: {@link GradeController#getMyClasses(String)}
     */
    @Test
    void testGetMyClasses2() throws Exception {
        ClassEntity classEntity = new ClassEntity();
        classEntity.setAssistId("42");
        classEntity.setId("42");
        classEntity.setRespId("42");
        classEntity.setStuNum(10);
        classEntity.setTeacherId("42");

        ArrayList<ClassEntity> classEntityList = new ArrayList<>();
        classEntityList.add(classEntity);
        when(this.gradeService.getMyClasses((String) any())).thenReturn(classEntityList);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/get/my/classes")
                .param("teacherId", "foo");
        MockMvcBuilders.standaloneSetup(this.gradeController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("[{\"id\":\"42\",\"respId\":\"42\",\"teacherId\":\"42\",\"assistId\":\"42\",\"stuNum\":10}]"));
    }

    /**
     * Method under test: {@link GradeController#getMyClasses(String)}
     */
    @Test
    void testGetMyClasses3() throws Exception {
        when(this.gradeService.getMyClasses((String) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/get/my/classes");
        getResult.contentType("https://example.org/example");
        MockHttpServletRequestBuilder requestBuilder = getResult.param("teacherId", "foo");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.gradeController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("????????"));
    }

    /**
     * Method under test: {@link GradeController#getIndividualGrade(String, Integer)}
     */
    @Test
    void testGetIndividualGrade2() throws Exception {
        LabGradeEntity labGradeEntity = new LabGradeEntity();
        labGradeEntity.setClassId("42");
        labGradeEntity.setLabId(123);
        labGradeEntity.setNote("Note");
        labGradeEntity.setScore(10.0d);
        labGradeEntity.setStuId("42");
        labGradeEntity.setTeacherId("42");
        labGradeEntity.setUpdateDate("2020-03-01");
        labGradeEntity.setVisible(true);
        when(this.gradeService.getParticularGrade((String) any(), (Integer) any())).thenReturn(labGradeEntity);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/get/grade/individual");
        getResult.contentType("https://example.org/example");
        MockHttpServletRequestBuilder requestBuilder = getResult.param("labId", String.valueOf(1)).param("stuId", "foo");
        MockMvcBuilders.standaloneSetup(this.gradeController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"stuId\":\"42\",\"classId\":\"42\",\"updateDate\":\"2020-03-01\",\"visible\":true,\"labId\":123,\"score\":10.0,\"note"
                                        + "\":\"Note\",\"teacherId\":\"42\"}"));
    }

    /**
     * Method under test: {@link GradeController#getReportDetail(ReportEntityPK)}
     */
    @Test
    void testGetReportDetail() throws Exception {
        when(this.gradeService.getReport((ReportEntityPK) any())).thenReturn(new HashMap<>());

        ReportEntityPK reportEntityPK = new ReportEntityPK();
        reportEntityPK.setLabId(123);
        reportEntityPK.setStuId("42");
        String content = (new ObjectMapper()).writeValueAsString(reportEntityPK);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/get/report/detail")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.gradeController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{}"));
    }

    /**
     * Method under test: {@link GradeController#getReportListByTeacherIdAndLabId(String, Integer)}
     */
    @Test
    void testGetReportListByTeacherIdAndLabId() throws Exception {
        when(this.gradeService.getReportByTeacherIdAndLabId((String) any(), (Integer) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/get/lab/report");
        MockHttpServletRequestBuilder requestBuilder = getResult.param("labId", String.valueOf(1))
                .param("teacherId", "foo");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.gradeController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("???????????"));
    }

    /**
     * Method under test: {@link GradeController#getReportListByTeacherIdAndLabId(String, Integer)}
     */
    @Test
    void testGetReportListByTeacherIdAndLabId2() throws Exception {
        ReportListEntity reportListEntity = new ReportListEntity();
        reportListEntity.setChecked(true);
        reportListEntity.setClassId("42");
        reportListEntity.setLabId(123);
        reportListEntity.setLabName("?");
        reportListEntity.setMutable(true);
        reportListEntity.setStuId("42");
        reportListEntity.setStuName("?");
        reportListEntity.setTeacherId("42");

        ArrayList<ReportListEntity> reportListEntityList = new ArrayList<>();
        reportListEntityList.add(reportListEntity);
        when(this.gradeService.getReportByTeacherIdAndLabId((String) any(), (Integer) any()))
                .thenReturn(reportListEntityList);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/get/lab/report");
        MockHttpServletRequestBuilder requestBuilder = getResult.param("labId", String.valueOf(1))
                .param("teacherId", "foo");
        MockMvcBuilders.standaloneSetup(this.gradeController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "[{\"stuId\":\"42\",\"labId\":123,\"classId\":\"42\",\"stuName\":\"?\",\"teacherId\":\"42\",\"labName\":\"?\",\"mutable\":true"
                                        + ",\"checked\":true}]"));
    }

    /**
     * Method under test: {@link GradeController#getReportListByTeacherIdAndLabId(String, Integer)}
     */
    @Test
    void testGetReportListByTeacherIdAndLabId3() throws Exception {
        when(this.gradeService.getReportByTeacherIdAndLabId((String) any(), (Integer) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/get/lab/report");
        getResult.contentType("https://example.org/example");
        MockHttpServletRequestBuilder requestBuilder = getResult.param("labId", String.valueOf(1))
                .param("teacherId", "foo");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.gradeController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("???????????"));
    }

    /**
     * Method under test: {@link GradeController#getReportPage(String, Integer, Integer)}
     */
    @Test
    void testGetReportPage() throws Exception {
        when(this.gradeService.getReportListPaged((String) any(), (Integer) any(), (Integer) any()))
                .thenReturn(new PageImpl<>(new ArrayList<>()));
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/get/report/page/{teacherId}", "42");
        MockHttpServletRequestBuilder paramResult = getResult.param("pageNum", String.valueOf(1));
        MockHttpServletRequestBuilder requestBuilder = paramResult.param("pageSize", String.valueOf(1));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.gradeController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("????????"));
    }

    /**
     * Method under test: {@link GradeController#getReportPage(String, Integer, Integer)}
     */
    @Test
    void testGetReportPage2() throws Exception {
        ReportListEntity reportListEntity = new ReportListEntity();
        reportListEntity.setChecked(true);
        reportListEntity.setClassId("42");
        reportListEntity.setLabId(123);
        reportListEntity.setLabName("?");
        reportListEntity.setMutable(true);
        reportListEntity.setStuId("42");
        reportListEntity.setStuName("?");
        reportListEntity.setTeacherId("42");

        ArrayList<ReportListEntity> reportListEntityList = new ArrayList<>();
        reportListEntityList.add(reportListEntity);
        PageImpl<ReportListEntity> pageImpl = new PageImpl<>(reportListEntityList);
        when(this.gradeService.getReportListPaged((String) any(), (Integer) any(), (Integer) any())).thenReturn(pageImpl);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/get/report/page/{teacherId}", "42");
        MockHttpServletRequestBuilder paramResult = getResult.param("pageNum", String.valueOf(1));
        MockHttpServletRequestBuilder requestBuilder = paramResult.param("pageSize", String.valueOf(1));
        MockMvcBuilders.standaloneSetup(this.gradeController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"data\":{\"content\":[{\"stuId\":\"42\",\"labId\":123,\"classId\":\"42\",\"stuName\":\"?\",\"teacherId\":\"42\",\"labName"
                                        + "\":\"?\",\"mutable\":true,\"checked\":true}],\"pageable\":\"INSTANCE\",\"last\":true,\"totalElements\":1,\"totalPages"
                                        + "\":1,\"sort\":{\"unsorted\":true,\"sorted\":false,\"empty\":true},\"first\":true,\"numberOfElements\":1,\"number\":0"
                                        + ",\"size\":1,\"empty\":false},\"pageNum\":1}"));
    }

    /**
     * Method under test: {@link GradeController#getReportPage(String, Integer, Integer)}
     */
    @Test
    void testGetReportPage3() throws Exception {
        when(this.gradeService.getReportListPaged((String) any(), (Integer) any(), (Integer) any())).thenReturn(null);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/get/report/page/{teacherId}", "42");
        MockHttpServletRequestBuilder paramResult = getResult.param("pageNum", String.valueOf(1));
        MockHttpServletRequestBuilder requestBuilder = paramResult.param("pageSize", String.valueOf(1));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.gradeController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400))
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("???????"));
    }

    /**
     * Method under test: {@link GradeController#getReportPage(String, Integer, Integer)}
     */
    @Test
    void testGetReportPage4() throws Exception {
        when(this.gradeService.getReportListPaged((String) any(), (Integer) any(), (Integer) any()))
                .thenReturn(new PageImpl<>(new ArrayList<>()));
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/get/report/page/{teacherId}", "42");
        MockHttpServletRequestBuilder paramResult = getResult.param("pageNum", String.valueOf(0));
        MockHttpServletRequestBuilder requestBuilder = paramResult.param("pageSize", String.valueOf(1));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.gradeController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("????????"));
    }

    /**
     * Method under test: {@link GradeController#getReportPage(String, Integer, Integer)}
     */
    @Test
    void testGetReportPage5() throws Exception {
        when(this.gradeService.getReportListPaged((String) any(), (Integer) any(), (Integer) any()))
                .thenReturn(new PageImpl<>(new ArrayList<>()));
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/get/report/page/{teacherId}", "42");
        MockHttpServletRequestBuilder paramResult = getResult.param("pageNum", String.valueOf(1));
        MockHttpServletRequestBuilder requestBuilder = paramResult.param("pageSize", String.valueOf(0));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.gradeController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("????????"));
    }

    /**
     * Method under test: {@link GradeController#getReportPage(String, Integer, Integer)}
     */
    @Test
    void testGetReportPage6() throws Exception {
        when(this.gradeService.getReportListPaged((String) any(), (Integer) any(), (Integer) any()))
                .thenReturn(new PageImpl<>(new ArrayList<>()));
        MockHttpServletRequestBuilder paramResult = MockMvcRequestBuilders.get("/api/get/report/page/{teacherId}", "42")
                .param("pageNum", null);
        MockHttpServletRequestBuilder requestBuilder = paramResult.param("pageSize", String.valueOf(1));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.gradeController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("????????"));
    }

    /**
     * Method under test: {@link GradeController#getReportPage(String, Integer, Integer)}
     */
    @Test
    void testGetReportPage7() throws Exception {
        when(this.gradeService.getReportListPaged((String) any(), (Integer) any(), (Integer) any()))
                .thenReturn(new PageImpl<>(new ArrayList<>()));
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/get/report/page/{teacherId}", "42");
        MockHttpServletRequestBuilder requestBuilder = getResult.param("pageNum", String.valueOf(1))
                .param("pageSize", null);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.gradeController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400))
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("???????"));
    }

    /**
     * Method under test: {@link GradeController#getReportPage(String, Integer, Integer)}
     */
    @Test
    void testGetReportPage8() throws Exception {
        when(this.gradeService.getReportListPaged((String) any(), (Integer) any(), (Integer) any()))
                .thenReturn(new PageImpl<>(new ArrayList<>()));
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/get/report/page/{teacherId}", "42");
        getResult.contentType("https://example.org/example");
        MockHttpServletRequestBuilder paramResult = getResult.param("pageNum", String.valueOf(1));
        MockHttpServletRequestBuilder requestBuilder = paramResult.param("pageSize", String.valueOf(1));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.gradeController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("????????"));
    }

    /**
     * Method under test: {@link GradeController#getSummatorDetail(String)}
     */
    @Test
    void testGetSummatorDetail() throws Exception {
        when(this.gradeService.getSummator((String) any())).thenReturn(new HashMap<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/get/summator/detail")
                .param("stuId", "foo");
        MockMvcBuilders.standaloneSetup(this.gradeController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{}"));
    }

    /**
     * Method under test: {@link GradeController#getSummatorDetail(String)}
     */
    @Test
    void testGetSummatorDetail2() throws Exception {
        when(this.gradeService.getSummator((String) any())).thenReturn(new HashMap<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/get/summator/detail");
        getResult.contentType("https://example.org/example");
        MockHttpServletRequestBuilder requestBuilder = getResult.param("stuId", "foo");
        MockMvcBuilders.standaloneSetup(this.gradeController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{}"));
    }

    /**
     * Method under test: {@link GradeController#getSummatorList(String, Integer, Integer)}
     */
    @Test
    void testGetSummatorList() throws Exception {
        when(this.gradeService.getSummatorListPaged((String) any(), (Integer) any(), (Integer) any()))
                .thenReturn(new PageImpl<>(new ArrayList<>()));
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/get/summator/list");
        MockHttpServletRequestBuilder paramResult = getResult.param("pageNum", String.valueOf(1));
        MockHttpServletRequestBuilder requestBuilder = paramResult.param("pageSize", String.valueOf(1))
                .param("teacherId", "foo");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.gradeController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("???????????"));
    }

    /**
     * Method under test: {@link GradeController#getSummatorList(String, Integer, Integer)}
     */
    @Test
    void testGetSummatorList2() throws Exception {
        SummatorListEntity summatorListEntity = new SummatorListEntity();
        summatorListEntity.setChecked(true);
        summatorListEntity.setClassId("42");
        summatorListEntity.setLabId(123);
        summatorListEntity.setLabName("?");
        summatorListEntity.setMutable(true);
        summatorListEntity.setStuId("42");
        summatorListEntity.setStuName("?");
        summatorListEntity.setTeacherId("42");

        ArrayList<SummatorListEntity> summatorListEntityList = new ArrayList<>();
        summatorListEntityList.add(summatorListEntity);
        PageImpl<SummatorListEntity> pageImpl = new PageImpl<>(summatorListEntityList);
        when(this.gradeService.getSummatorListPaged((String) any(), (Integer) any(), (Integer) any())).thenReturn(pageImpl);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/get/summator/list");
        MockHttpServletRequestBuilder paramResult = getResult.param("pageNum", String.valueOf(1));
        MockHttpServletRequestBuilder requestBuilder = paramResult.param("pageSize", String.valueOf(1))
                .param("teacherId", "foo");
        MockMvcBuilders.standaloneSetup(this.gradeController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"content\":[{\"stuId\":\"42\",\"labId\":123,\"classId\":\"42\",\"stuName\":\"?\",\"teacherId\":\"42\",\"labName\":\"?\","
                                        + "\"mutable\":true,\"checked\":true}],\"pageable\":\"INSTANCE\",\"last\":true,\"totalElements\":1,\"totalPages\":1,"
                                        + "\"sort\":{\"unsorted\":true,\"sorted\":false,\"empty\":true},\"first\":true,\"numberOfElements\":1,\"number\":0,"
                                        + "\"size\":1,\"empty\":false}"));
    }

    /**
     * Method under test: {@link GradeController#getSummatorList(String, Integer, Integer)}
     */
    @Test
    void testGetSummatorList3() throws Exception {
        when(this.gradeService.getSummatorListPaged((String) any(), (Integer) any(), (Integer) any())).thenReturn(null);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/get/summator/list");
        MockHttpServletRequestBuilder paramResult = getResult.param("pageNum", String.valueOf(1));
        MockHttpServletRequestBuilder requestBuilder = paramResult.param("pageSize", String.valueOf(1))
                .param("teacherId", "foo");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.gradeController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400))
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("???????"));
    }

    /**
     * Method under test: {@link GradeController#getSummatorList(String, Integer, Integer)}
     */
    @Test
    void testGetSummatorList4() throws Exception {
        when(this.gradeService.getSummatorListPaged((String) any(), (Integer) any(), (Integer) any()))
                .thenReturn(new PageImpl<>(new ArrayList<>()));
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/get/summator/list");
        getResult.contentType("https://example.org/example");
        MockHttpServletRequestBuilder paramResult = getResult.param("pageNum", String.valueOf(1));
        MockHttpServletRequestBuilder requestBuilder = paramResult.param("pageSize", String.valueOf(1))
                .param("teacherId", "foo");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.gradeController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("???????????"));
    }

    /**
     * Method under test: {@link GradeController#releaseGrade(String)}
     */
    @Test
    void testReleaseGrade() throws Exception {
        when(this.gradeService.release((String) any())).thenReturn("1.0.2");
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/post/release/grade")
                .param("classId", "foo");
        MockMvcBuilders.standaloneSetup(this.gradeController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("1.0.2"));
    }

    /**
     * Method under test: {@link GradeController#releaseGrade(String)}
     */
    @Test
    void testReleaseGrade2() throws Exception {
        when(this.gradeService.release((String) any())).thenReturn("1.0.2");
        MockHttpServletRequestBuilder postResult = MockMvcRequestBuilders.post("/api/post/release/grade");
        postResult.contentType("https://example.org/example");
        MockHttpServletRequestBuilder requestBuilder = postResult.param("classId", "foo");
        MockMvcBuilders.standaloneSetup(this.gradeController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("1.0.2"));
    }

    /**
     * Method under test: {@link GradeController#releaseIndividual(GradeDto)}
     */
    @Test
    void testReleaseIndividual() throws Exception {
        when(this.gradeService.release((GradeDto) any())).thenReturn("1.0.2");

        GradeDto gradeDto = new GradeDto();
        gradeDto.setClassId("42");
        gradeDto.setLabId(123);
        gradeDto.setNote("Note");
        gradeDto.setScore(10.0d);
        gradeDto.setStuId("42");
        gradeDto.setTeacherId("42");
        String content = (new ObjectMapper()).writeValueAsString(gradeDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/post/release/individual")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.gradeController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("1.0.2"));
    }

    /**
     * Method under test: {@link GradeController#saveGrade(GradeDto)}
     */
    @Test
    void testSaveGrade() throws Exception {
        when(this.gradeService.save((GradeDto) any())).thenReturn("Save");

        GradeDto gradeDto = new GradeDto();
        gradeDto.setClassId("42");
        gradeDto.setLabId(123);
        gradeDto.setNote("Note");
        gradeDto.setScore(10.0d);
        gradeDto.setStuId("42");
        gradeDto.setTeacherId("42");
        String content = (new ObjectMapper()).writeValueAsString(gradeDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/post/save/grade")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.gradeController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Save"));
    }
}

