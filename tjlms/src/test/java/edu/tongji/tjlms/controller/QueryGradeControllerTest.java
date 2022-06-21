package edu.tongji.tjlms.controller;

import edu.tongji.tjlms.dto.FinalGradeDto;
import edu.tongji.tjlms.dto.QueryGradeDto;
import edu.tongji.tjlms.model.QueryGradeEntity;
import edu.tongji.tjlms.service.grade.QueryGradeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {QueryGradeController.class})
@ExtendWith(SpringExtension.class)
class QueryGradeControllerTest {
    @Autowired
    private QueryGradeController queryGradeController;

    @MockBean
    private QueryGradeService queryGradeService;

    /**
     * Method under test: {@link QueryGradeController#getGrades(String)}
     */
    @Test
    void testGetGrades() throws Exception {
        FinalGradeDto finalGradeDto = new FinalGradeDto();
        finalGradeDto.setAttendance(10.0d);
        finalGradeDto.setEachGrades(new ArrayList<>());
        finalGradeDto.setFinalGrade("Final Grade");
        finalGradeDto.setFinalScore(10.0d);
        when(this.queryGradeService.queryFinalGrade((String) any())).thenReturn(finalGradeDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/get/grade/{studentId}", "42");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.queryGradeController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("??????"));
    }

    /**
     * Method under test: {@link QueryGradeController#getGrades(String)}
     */
    @Test
    void testGetGrades2() throws Exception {
        ArrayList<QueryGradeDto> queryGradeDtoList = new ArrayList<>();
        queryGradeDtoList.add(new QueryGradeDto(new QueryGradeEntity(), "?"));

        FinalGradeDto finalGradeDto = new FinalGradeDto();
        finalGradeDto.setAttendance(10.0d);
        finalGradeDto.setEachGrades(queryGradeDtoList);
        finalGradeDto.setFinalGrade("Final Grade");
        finalGradeDto.setFinalScore(10.0d);
        when(this.queryGradeService.queryFinalGrade((String) any())).thenReturn(finalGradeDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/get/grade/{studentId}", "42");
        MockMvcBuilders.standaloneSetup(this.queryGradeController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"eachGrades\":[{\"queryGradeEntity\":{\"stuId\":null,\"name\":null,\"score\":null,\"note\":null,\"updateDate\""
                                        + ":null,\"labId\":0},\"grade\":\"?\"}],\"attendance\":10.0,\"finalScore\":10.0,\"finalGrade\":\"Final Grade\"}"));
    }

    /**
     * Method under test: {@link QueryGradeController#getGrades(String)}
     */
    @Test
    void testGetGrades3() throws Exception {
        FinalGradeDto finalGradeDto = new FinalGradeDto();
        finalGradeDto.setAttendance(10.0d);
        finalGradeDto.setEachGrades(new ArrayList<>());
        finalGradeDto.setFinalGrade("Final Grade");
        finalGradeDto.setFinalScore(10.0d);
        when(this.queryGradeService.queryFinalGrade((String) any())).thenReturn(finalGradeDto);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/get/grade/{studentId}", "42");
        getResult.contentType("https://example.org/example");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.queryGradeController)
                .build()
                .perform(getResult);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("??????"));
    }

    /**
     * Method under test: {@link QueryGradeController#getParticularGrade(String, Integer)}
     */
    @Test
    void testGetParticularGrade() throws Exception {
        QueryGradeEntity queryGradeEntity = new QueryGradeEntity();
        queryGradeEntity.setLabId(123);
        queryGradeEntity.setName("Name");
        queryGradeEntity.setNote("Note");
        queryGradeEntity.setScore(10.0d);
        queryGradeEntity.setStuId("42");
        queryGradeEntity.setUpdateDate("2020-03-01");
        when(this.queryGradeService.queryParticularGrade((String) any(), (Integer) any())).thenReturn(queryGradeEntity);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/get/particular/grade");
        MockHttpServletRequestBuilder requestBuilder = getResult.param("labId", String.valueOf(1)).param("stuId", "foo");
        MockMvcBuilders.standaloneSetup(this.queryGradeController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"stuId\":\"42\",\"name\":\"Name\",\"score\":10.0,\"note\":\"Note\",\"updateDate\":\"2020-03-01\",\"labId\":123}"));
    }

    /**
     * Method under test: {@link QueryGradeController#getParticularGrade(String, Integer)}
     */
    @Test
    void testGetParticularGrade2() throws Exception {
        QueryGradeEntity queryGradeEntity = new QueryGradeEntity();
        queryGradeEntity.setLabId(123);
        queryGradeEntity.setName("Name");
        queryGradeEntity.setNote("Note");
        queryGradeEntity.setScore(10.0d);
        queryGradeEntity.setStuId("42");
        queryGradeEntity.setUpdateDate("2020-03-01");
        when(this.queryGradeService.queryParticularGrade((String) any(), (Integer) any())).thenReturn(queryGradeEntity);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/get/particular/grade");
        getResult.contentType("https://example.org/example");
        MockHttpServletRequestBuilder requestBuilder = getResult.param("labId", String.valueOf(1)).param("stuId", "foo");
        MockMvcBuilders.standaloneSetup(this.queryGradeController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"stuId\":\"42\",\"name\":\"Name\",\"score\":10.0,\"note\":\"Note\",\"updateDate\":\"2020-03-01\",\"labId\":123}"));
    }
}

