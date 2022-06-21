package edu.tongji.tjlms.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.tongji.tjlms.dto.PostCheckDto;
import edu.tongji.tjlms.service.check.CheckService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
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

@ContextConfiguration(classes = {CheckController.class})
@ExtendWith(SpringExtension.class)
class CheckControllerTest {
    @Autowired
    private CheckController checkController;

    @MockBean
    private CheckService checkService;

    /**
     * Method under test: {@link CheckController#checkIn(String, Integer)}
     */
    @Test
    void testCheckIn() throws Exception {
        when(this.checkService.submitCheck((String) any(), (Integer) any())).thenReturn("Submit Check");
        MockHttpServletRequestBuilder postResult = MockMvcRequestBuilders.post("/api/check/in");
        MockHttpServletRequestBuilder requestBuilder = postResult.param("checkId", String.valueOf(1)).param("stuId", "foo");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.checkController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400))
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Submit Check"));
    }

    /**
     * Method under test: {@link CheckController#postCheck(PostCheckDto)}
     */
    @Test
    void testPostCheck() throws Exception {
        when(this.checkService.postCheck((PostCheckDto) any())).thenReturn("Post Check");

        PostCheckDto postCheckDto = new PostCheckDto();
        postCheckDto.setClassId("42");
        postCheckDto.setEnd("End");
        postCheckDto.setStart("Start");
        String content = (new ObjectMapper()).writeValueAsString(postCheckDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/post/check")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.checkController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Post Check"));
    }

    /**
     * Method under test: {@link CheckController#checkIn(String, Integer)}
     */
    @Test
    void testCheckIn2() throws Exception {
        when(this.checkService.submitCheck((String) any(), (Integer) any())).thenReturn("签到成功");
        MockHttpServletRequestBuilder postResult = MockMvcRequestBuilders.post("/api/check/in");
        MockHttpServletRequestBuilder requestBuilder = postResult.param("checkId", String.valueOf(1)).param("stuId", "foo");
        MockMvcBuilders.standaloneSetup(this.checkController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("????"));
    }

    /**
     * Method under test: {@link CheckController#checkIn(String, Integer)}
     */
    @Test
    void testCheckIn3() throws Exception {
        when(this.checkService.submitCheck((String) any(), (Integer) any())).thenReturn("Submit Check");
        MockHttpServletRequestBuilder postResult = MockMvcRequestBuilders.post("/api/check/in");
        postResult.contentType("https://example.org/example");
        MockHttpServletRequestBuilder requestBuilder = postResult.param("checkId", String.valueOf(1)).param("stuId", "foo");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.checkController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400))
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Submit Check"));
    }

    /**
     * Method under test: {@link CheckController#getCheckStu(String)}
     */
    @Test
    void testGetCheckStu() throws Exception {
        when(this.checkService.getAllCheckByStuId((String) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/stu/get/check")
                .param("stuId", "foo");
        MockMvcBuilders.standaloneSetup(this.checkController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link CheckController#getCheckStu(String)}
     */
    @Test
    void testGetCheckStu2() throws Exception {
        when(this.checkService.getAllCheckByStuId((String) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/stu/get/check");
        getResult.contentType("https://example.org/example");
        MockHttpServletRequestBuilder requestBuilder = getResult.param("stuId", "foo");
        MockMvcBuilders.standaloneSetup(this.checkController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link CheckController#getCheckTeacher(String)}
     */
    @Test
    void testGetCheckTeacher() throws Exception {
        when(this.checkService.getAllCheckByClassId((String) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/teacher/get/check")
                .param("classId", "foo");
        MockMvcBuilders.standaloneSetup(this.checkController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link CheckController#getCheckTeacher(String)}
     */
    @Test
    void testGetCheckTeacher2() throws Exception {
        when(this.checkService.getAllCheckByClassId((String) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/teacher/get/check");
        getResult.contentType("https://example.org/example");
        MockHttpServletRequestBuilder requestBuilder = getResult.param("classId", "foo");
        MockMvcBuilders.standaloneSetup(this.checkController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }
}

