package edu.tongji.tjlms.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.tongji.tjlms.dto.UploadReportDto;
import edu.tongji.tjlms.model.ReportFileEntity;
import edu.tongji.tjlms.model.ReportFileEntityPK;
import edu.tongji.tjlms.service.reportfile.ReportFileService;
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

@ContextConfiguration(classes = {ReportFileController.class})
@ExtendWith(SpringExtension.class)
class ReportFileControllerTest {
    @Autowired
    private ReportFileController reportFileController;

    @MockBean
    private ReportFileService reportFileService;

    /**
     * Method under test: {@link ReportFileController#getAllByStuId(Integer)}
     */
    @Test
    void testGetAllByStuId() throws Exception {
        when(this.reportFileService.getAllByLabId((Integer) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/get/reportFileLab/{labId}", 123);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.reportFileController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("??????"));
    }

    /**
     * Method under test: {@link ReportFileController#getAllByStuId(String)}
     */
    @Test
    void testGetAllByStuId3() throws Exception {
        when(this.reportFileService.getAllByStuId((String) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/get/reportFileStudent/{studentId}",
                "42");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.reportFileController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("??????"));
    }

    /**
     * Method under test: {@link ReportFileController#getAllByStuId(String)}
     */
    @Test
    void testGetAllByStuId4() throws Exception {
        ReportFileEntity reportFileEntity = new ReportFileEntity();
        reportFileEntity.setLabId(123);
        reportFileEntity.setLocation("?");
        reportFileEntity.setMutable(true);
        reportFileEntity.setName("?");
        reportFileEntity.setStuId("42");
        reportFileEntity.setUploadTime("?");

        ArrayList<ReportFileEntity> reportFileEntityList = new ArrayList<>();
        reportFileEntityList.add(reportFileEntity);
        when(this.reportFileService.getAllByStuId((String) any())).thenReturn(reportFileEntityList);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/get/reportFileStudent/{studentId}",
                "42");
        MockMvcBuilders.standaloneSetup(this.reportFileController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "[{\"stuId\":\"42\",\"labId\":123,\"location\":\"?\",\"name\":\"?\",\"uploadTime\":\"?\",\"mutable\":true}]"));
    }

    /**
     * Method under test: {@link ReportFileController#getAllByStuId(String)}
     */
    @Test
    void testGetAllByStuId5() throws Exception {
        when(this.reportFileService.getAllByStuId((String) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/get/reportFileStudent/{studentId}",
                "42");
        getResult.contentType("https://example.org/example");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.reportFileController)
                .build()
                .perform(getResult);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("??????"));
    }

    /**
     * Method under test: {@link ReportFileController#getAllByStuId(Integer)}
     */
    @Test
    void testGetAllByStuId2() throws Exception {
        ReportFileEntity reportFileEntity = new ReportFileEntity();
        reportFileEntity.setLabId(123);
        reportFileEntity.setLocation("?");
        reportFileEntity.setMutable(true);
        reportFileEntity.setName("?");
        reportFileEntity.setStuId("42");
        reportFileEntity.setUploadTime("?");

        ArrayList<ReportFileEntity> reportFileEntityList = new ArrayList<>();
        reportFileEntityList.add(reportFileEntity);
        when(this.reportFileService.getAllByLabId((Integer) any())).thenReturn(reportFileEntityList);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/get/reportFileLab/{labId}", 123);
        MockMvcBuilders.standaloneSetup(this.reportFileController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "[{\"stuId\":\"42\",\"labId\":123,\"location\":\"?\",\"name\":\"?\",\"uploadTime\":\"?\",\"mutable\":true}]"));
    }

    /**
     * Method under test: {@link ReportFileController#getByPK(String, Integer)}
     */
    @Test
    void testGetByPK() throws Exception {
        ReportFileEntity reportFileEntity = new ReportFileEntity();
        reportFileEntity.setLabId(123);
        reportFileEntity.setLocation("Location");
        reportFileEntity.setMutable(true);
        reportFileEntity.setName("Name");
        reportFileEntity.setStuId("42");
        reportFileEntity.setUploadTime("Upload Time");
        when(this.reportFileService.getByStuIdAndLabId((String) any(), (Integer) any())).thenReturn(reportFileEntity);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/get/reportFilePK");
        MockHttpServletRequestBuilder requestBuilder = getResult.param("labId", String.valueOf(1)).param("stuId", "foo");
        MockMvcBuilders.standaloneSetup(this.reportFileController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"stuId\":\"42\",\"labId\":123,\"location\":\"Location\",\"name\":\"Name\",\"uploadTime\":\"Upload Time\",\"mutable"
                                        + "\":true}"));
    }

    /**
     * Method under test: {@link ReportFileController#getByPK(String, Integer)}
     */
    @Test
    void testGetByPK2() throws Exception {
        ReportFileEntity reportFileEntity = new ReportFileEntity();
        reportFileEntity.setLabId(123);
        reportFileEntity.setLocation("Location");
        reportFileEntity.setMutable(true);
        reportFileEntity.setName("Name");
        reportFileEntity.setStuId("42");
        reportFileEntity.setUploadTime("Upload Time");
        when(this.reportFileService.getByStuIdAndLabId((String) any(), (Integer) any())).thenReturn(reportFileEntity);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/get/reportFilePK");
        getResult.contentType("https://example.org/example");
        MockHttpServletRequestBuilder requestBuilder = getResult.param("labId", String.valueOf(1)).param("stuId", "foo");
        MockMvcBuilders.standaloneSetup(this.reportFileController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"stuId\":\"42\",\"labId\":123,\"location\":\"Location\",\"name\":\"Name\",\"uploadTime\":\"Upload Time\",\"mutable"
                                        + "\":true}"));
    }

    /**
     * Method under test: {@link ReportFileController#saveReportFile(UploadReportDto)}
     */
    @Test
    void testSaveReportFile() throws Exception {
        when(this.reportFileService.saveFile((UploadReportDto) any())).thenReturn("Save File");

        UploadReportDto uploadReportDto = new UploadReportDto();
        uploadReportDto.setLabId(123);
        uploadReportDto.setLocation("Location");
        uploadReportDto.setName("Name");
        uploadReportDto.setStuId("42");
        String content = (new ObjectMapper()).writeValueAsString(uploadReportDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/save/report/file")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.reportFileController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Save File"));
    }

    /**
     * Method under test: {@link ReportFileController#submitReportFile(ReportFileEntityPK)}
     */
    @Test
    void testSubmitReportFile() throws Exception {
        when(this.reportFileService.submitFile((ReportFileEntityPK) any())).thenReturn("Submit File");

        ReportFileEntityPK reportFileEntityPK = new ReportFileEntityPK();
        reportFileEntityPK.setLabId(123);
        reportFileEntityPK.setStuId("42");
        String content = (new ObjectMapper()).writeValueAsString(reportFileEntityPK);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/submit/report/file")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.reportFileController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Submit File"));
    }
}

