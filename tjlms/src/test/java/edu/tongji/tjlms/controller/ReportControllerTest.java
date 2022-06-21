package edu.tongji.tjlms.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.tongji.tjlms.dto.SubmitReportDto;
import edu.tongji.tjlms.model.ReportEntityPK;
import edu.tongji.tjlms.service.report.ReportService;
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

@ContextConfiguration(classes = {ReportController.class})
@ExtendWith(SpringExtension.class)
class ReportControllerTest {
    @Autowired
    private ReportController reportController;

    @MockBean
    private ReportService reportService;

    /**
     * Method under test: {@link ReportController#saveReport(SubmitReportDto)}
     */
    @Test
    void testSaveReport() throws Exception {
        when(this.reportService.saveReport((SubmitReportDto) any())).thenReturn("Save Report");

        SubmitReportDto submitReportDto = new SubmitReportDto();
        submitReportDto.setAim("Aim");
        submitReportDto.setLabId(123);
        submitReportDto.setPrinciple("Principle");
        submitReportDto.setResult("Result");
        submitReportDto.setStep("Step");
        submitReportDto.setStuId("42");
        String content = (new ObjectMapper()).writeValueAsString(submitReportDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/post/save/report")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.reportController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Save Report"));
    }

    /**
     * Method under test: {@link ReportController#submitReport(SubmitReportDto)}
     */
    @Test
    void testSubmitReport() throws Exception {
        when(this.reportService.submitReport((SubmitReportDto) any())).thenReturn("Submit Report");

        SubmitReportDto submitReportDto = new SubmitReportDto();
        submitReportDto.setAim("Aim");
        submitReportDto.setLabId(123);
        submitReportDto.setPrinciple("Principle");
        submitReportDto.setResult("Result");
        submitReportDto.setStep("Step");
        submitReportDto.setStuId("42");
        String content = (new ObjectMapper()).writeValueAsString(submitReportDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/post/submit/report")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.reportController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Submit Report"));
    }

    /**
     * Method under test: {@link ReportController#submitReport(SubmitReportDto)}
     */
    @Test
    void testSubmitReport2() throws Exception {
        when(this.reportService.submitReport((SubmitReportDto) any())).thenReturn("提交成功");

        SubmitReportDto submitReportDto = new SubmitReportDto();
        submitReportDto.setAim("Aim");
        submitReportDto.setLabId(123);
        submitReportDto.setPrinciple("Principle");
        submitReportDto.setResult("Result");
        submitReportDto.setStep("Step");
        submitReportDto.setStuId("42");
        String content = (new ObjectMapper()).writeValueAsString(submitReportDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/post/submit/report")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.reportController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("????"));
    }

    /**
     * Method under test: {@link ReportController#getInfo(String)}
     */
    @Test
    void testGetInfo() throws Exception {
        when(this.reportService.getInfo((String) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/get/report/info")
                .param("id", "foo");
        MockMvcBuilders.standaloneSetup(this.reportController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link ReportController#getInfo(String)}
     */
    @Test
    void testGetInfo2() throws Exception {
        when(this.reportService.getInfo((String) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/get/report/info");
        getResult.contentType("https://example.org/example");
        MockHttpServletRequestBuilder requestBuilder = getResult.param("id", "foo");
        MockMvcBuilders.standaloneSetup(this.reportController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link ReportController#getContent(ReportEntityPK)}
     */
    @Test
    void testGetContent() throws Exception {
        when(this.reportService.getContent((ReportEntityPK) any())).thenReturn(new ArrayList<>());

        ReportEntityPK reportEntityPK = new ReportEntityPK();
        reportEntityPK.setLabId(123);
        reportEntityPK.setStuId("42");
        String content = (new ObjectMapper()).writeValueAsString(reportEntityPK);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/post/report/content")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.reportController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }
}

