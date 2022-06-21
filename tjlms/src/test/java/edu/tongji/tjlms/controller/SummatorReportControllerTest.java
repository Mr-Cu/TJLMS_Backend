package edu.tongji.tjlms.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.tongji.tjlms.dto.ReportInfoDto;
import edu.tongji.tjlms.dto.SubmitSummatorDto;
import edu.tongji.tjlms.dto.SummatorBasicDto;
import edu.tongji.tjlms.service.sumreport.SummatorReportService;
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

@ContextConfiguration(classes = {SummatorReportController.class})
@ExtendWith(SpringExtension.class)
class SummatorReportControllerTest {
    @Autowired
    private SummatorReportController summatorReportController;

    @MockBean
    private SummatorReportService summatorReportService;

    /**
     * Method under test: {@link SummatorReportController#getSummatorContent(String)}
     */
    @Test
    void testGetSummatorContent() throws Exception {
        when(this.summatorReportService.getContent((String) any())).thenReturn(new HashMap<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/get/summator/content")
                .param("id", "foo");
        MockMvcBuilders.standaloneSetup(this.summatorReportController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{}"));
    }

    /**
     * Method under test: {@link SummatorReportController#getSummatorContent(String)}
     */
    @Test
    void testGetSummatorContent2() throws Exception {
        when(this.summatorReportService.getContent((String) any())).thenReturn(new HashMap<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/get/summator/content");
        getResult.contentType("https://example.org/example");
        MockHttpServletRequestBuilder requestBuilder = getResult.param("id", "foo");
        MockMvcBuilders.standaloneSetup(this.summatorReportController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{}"));
    }

    /**
     * Method under test: {@link SummatorReportController#getSummatorInfo(String)}
     */
    @Test
    void testGetSummatorInfo() throws Exception {
        ReportInfoDto reportInfoDto = new ReportInfoDto();
        reportInfoDto.setIsChecked(true);
        reportInfoDto.setLabName("Lab Name");
        reportInfoDto.setMutable(true);
        reportInfoDto.setUpdateDate("2020-03-01");
        when(this.summatorReportService.getInfo((String) any())).thenReturn(reportInfoDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/get/summator/info")
                .param("id", "foo");
        MockMvcBuilders.standaloneSetup(this.summatorReportController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"labName\":\"Lab Name\",\"updateDate\":\"2020-03-01\",\"mutable\":true,\"isChecked\":true}"));
    }

    /**
     * Method under test: {@link SummatorReportController#getSummatorInfo(String)}
     */
    @Test
    void testGetSummatorInfo2() throws Exception {
        ReportInfoDto reportInfoDto = new ReportInfoDto();
        reportInfoDto.setIsChecked(true);
        reportInfoDto.setLabName("Lab Name");
        reportInfoDto.setMutable(true);
        reportInfoDto.setUpdateDate("2020-03-01");
        when(this.summatorReportService.getInfo((String) any())).thenReturn(reportInfoDto);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/get/summator/info");
        getResult.contentType("https://example.org/example");
        MockHttpServletRequestBuilder requestBuilder = getResult.param("id", "foo");
        MockMvcBuilders.standaloneSetup(this.summatorReportController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"labName\":\"Lab Name\",\"updateDate\":\"2020-03-01\",\"mutable\":true,\"isChecked\":true}"));
    }

    /**
     * Method under test: {@link SummatorReportController#postSummator(SubmitSummatorDto)}
     */
    @Test
    void testPostSummator() throws Exception {
        when(this.summatorReportService.saveReport((SubmitSummatorDto) any())).thenReturn("Save Report");

        SummatorBasicDto summatorBasicDto = new SummatorBasicDto();
        summatorBasicDto.setAim("Aim");
        summatorBasicDto.setConclusion("Conclusion");
        summatorBasicDto.setPrinciple("Principle");
        summatorBasicDto.setStep("Step");
        summatorBasicDto.setStuId("42");

        SubmitSummatorDto submitSummatorDto = new SubmitSummatorDto();
        submitSummatorDto.setResultList(new ArrayList<>());
        submitSummatorDto.setSummatorBasicDto(summatorBasicDto);
        String content = (new ObjectMapper()).writeValueAsString(submitSummatorDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/save/summator")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.summatorReportController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Save Report"));
    }

    /**
     * Method under test: {@link SummatorReportController#submitSummator(SubmitSummatorDto)}
     */
    @Test
    void testSubmitSummator() throws Exception {
        when(this.summatorReportService.submitReport((SubmitSummatorDto) any())).thenReturn("Submit Report");

        SummatorBasicDto summatorBasicDto = new SummatorBasicDto();
        summatorBasicDto.setAim("Aim");
        summatorBasicDto.setConclusion("Conclusion");
        summatorBasicDto.setPrinciple("Principle");
        summatorBasicDto.setStep("Step");
        summatorBasicDto.setStuId("42");

        SubmitSummatorDto submitSummatorDto = new SubmitSummatorDto();
        submitSummatorDto.setResultList(new ArrayList<>());
        submitSummatorDto.setSummatorBasicDto(summatorBasicDto);
        String content = (new ObjectMapper()).writeValueAsString(submitSummatorDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/submit/summator")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.summatorReportController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Submit Report"));
    }
}

