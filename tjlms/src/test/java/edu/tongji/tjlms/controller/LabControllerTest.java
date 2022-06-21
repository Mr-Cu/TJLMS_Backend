package edu.tongji.tjlms.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.tongji.tjlms.dto.LabDto;
import edu.tongji.tjlms.model.LabEntity;
import edu.tongji.tjlms.service.lab.LabService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.HashMap;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {LabController.class})
@ExtendWith(SpringExtension.class)
class LabControllerTest {
    @Autowired
    private LabController labController;

    @MockBean
    private LabService labService;

    /**
     * Method under test: {@link LabController#releaseLab(LabDto)}
     */
    @Test
    void testReleaseLab() throws Exception {
        when(this.labService.releaseLab((LabDto) any())).thenReturn(1);

        LabDto labDto = new LabDto();
        labDto.setDeadline("Deadline");
        labDto.setIntro("Intro");
        labDto.setName("Name");
        labDto.setReleaseTeacher("1.0.2");
        String content = (new ObjectMapper()).writeValueAsString(labDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/release/lab")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.labController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("1"));
    }

    /**
     * Method under test: {@link LabController#getAllLabs()}
     */
    @Test
    void testGetAllLabs() throws Exception {
        when(this.labService.getAllWithNames()).thenReturn(new HashMap<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/get/all/labs");
        MockMvcBuilders.standaloneSetup(this.labController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{}"));
    }

    /**
     * Method under test: {@link LabController#getSchedule()}
     */
    @Test
    void testGetSchedule() throws Exception {
        when(this.labService.getSchedule()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/get/schedule");
        MockMvcBuilders.standaloneSetup(this.labController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link LabController#getSchedule()}
     */
    @Test
    void testGetSchedule2() throws Exception {
        when(this.labService.getSchedule()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/get/schedule");
        getResult.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(this.labController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link LabController#getAllLabs()}
     */
    @Test
    void testGetAllLabs2() throws Exception {
        when(this.labService.getAllWithNames()).thenReturn(new HashMap<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/get/all/labs");
        getResult.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(this.labController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{}"));
    }

    /**
     * Method under test: {@link LabController#getLabById(Integer)}
     */
    @Test
    void testGetLabById() throws Exception {
        LabEntity labEntity = new LabEntity();
        labEntity.setDeadline("Deadline");
        labEntity.setId(1);
        labEntity.setIntro("Intro");
        labEntity.setName("Name");
        labEntity.setReleaseDate("2020-03-01");
        labEntity.setReleaseTeacher("1.0.2");
        when(this.labService.getById((Integer) any())).thenReturn(labEntity);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/get/lab/{labId}", 123);
        MockMvcBuilders.standaloneSetup(this.labController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"name\":\"Name\",\"intro\":\"Intro\",\"releaseTeacher\":\"1.0.2\",\"releaseDate\":\"2020-03-01\",\"deadline\""
                                        + ":\"Deadline\"}"));
    }

    /**
     * Method under test: {@link LabController#getLabById(Integer)}
     */
    @Test
    void testGetLabById2() throws Exception {
        LabEntity labEntity = new LabEntity();
        labEntity.setDeadline("Deadline");
        labEntity.setId(1);
        labEntity.setIntro("Intro");
        labEntity.setName("Name");
        labEntity.setReleaseDate("2020-03-01");
        labEntity.setReleaseTeacher("1.0.2");
        when(this.labService.getById((Integer) any())).thenReturn(labEntity);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/get/lab/{labId}", 123);
        getResult.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(this.labController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"name\":\"Name\",\"intro\":\"Intro\",\"releaseTeacher\":\"1.0.2\",\"releaseDate\":\"2020-03-01\",\"deadline\""
                                        + ":\"Deadline\"}"));
    }
}

