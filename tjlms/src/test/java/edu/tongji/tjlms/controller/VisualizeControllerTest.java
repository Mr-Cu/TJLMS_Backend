package edu.tongji.tjlms.controller;

import edu.tongji.tjlms.service.visualize.VisualizeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashMap;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {VisualizeController.class})
@ExtendWith(SpringExtension.class)
class VisualizeControllerTest {
    @Autowired
    private VisualizeController visualizeController;

    @MockBean
    private VisualizeService visualizeService;

    /**
     * Method under test: {@link VisualizeController#getGradeByClassAndLab(String, Integer)}
     */
    @Test
    void testGetGradeByClassAndLab() throws Exception {
        when(this.visualizeService.getGradeByClassAndLab((String) any(), (Integer) any())).thenReturn(new HashMap<>());
        MockHttpServletRequestBuilder paramResult = MockMvcRequestBuilders.get("/api/visualize/grade/class")
                .param("classId", "foo");
        MockHttpServletRequestBuilder requestBuilder = paramResult.param("labId", String.valueOf(1));
        MockMvcBuilders.standaloneSetup(this.visualizeController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{}"));
    }

    /**
     * Method under test: {@link VisualizeController#getGradeByClassAndLab(String, Integer)}
     */
    @Test
    void testGetGradeByClassAndLab2() throws Exception {
        when(this.visualizeService.getGradeByClassAndLab((String) any(), (Integer) any())).thenReturn(new HashMap<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/visualize/grade/class");
        getResult.contentType("https://example.org/example");
        MockHttpServletRequestBuilder paramResult = getResult.param("classId", "foo");
        MockHttpServletRequestBuilder requestBuilder = paramResult.param("labId", String.valueOf(1));
        MockMvcBuilders.standaloneSetup(this.visualizeController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{}"));
    }

    /**
     * Method under test: {@link VisualizeController#getGradeByLabId(Integer)}
     */
    @Test
    void testGetGradeByLabId() throws Exception {
        when(this.visualizeService.getAllGradeByLab((Integer) any())).thenReturn(new HashMap<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/visualize/grade/{labId}", 123);
        MockMvcBuilders.standaloneSetup(this.visualizeController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{}"));
    }

    /**
     * Method under test: {@link VisualizeController#getGradeByLabId(Integer)}
     */
    @Test
    void testGetGradeByLabId2() throws Exception {
        when(this.visualizeService.getAllGradeByLab((Integer) any())).thenReturn(new HashMap<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/visualize/grade/{labId}", 123);
        getResult.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(this.visualizeController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{}"));
    }

    /**
     * Method under test: {@link VisualizeController#getSubmission(String, Integer)}
     */
    @Test
    void testGetSubmission() throws Exception {
        when(this.visualizeService.getSubmissionByClassAndLab((String) any(), (Integer) any())).thenReturn(new HashMap<>());
        MockHttpServletRequestBuilder paramResult = MockMvcRequestBuilders.get("/api/visualize/submission")
                .param("classId", "foo");
        MockHttpServletRequestBuilder requestBuilder = paramResult.param("labId", String.valueOf(1));
        MockMvcBuilders.standaloneSetup(this.visualizeController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{}"));
    }

    /**
     * Method under test: {@link VisualizeController#getSubmission(String, Integer)}
     */
    @Test
    void testGetSubmission2() throws Exception {
        when(this.visualizeService.getSubmissionByClassAndLab((String) any(), (Integer) any())).thenReturn(new HashMap<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/visualize/submission");
        getResult.contentType("https://example.org/example");
        MockHttpServletRequestBuilder paramResult = getResult.param("classId", "foo");
        MockHttpServletRequestBuilder requestBuilder = paramResult.param("labId", String.valueOf(1));
        MockMvcBuilders.standaloneSetup(this.visualizeController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{}"));
    }
}

