package edu.tongji.tjlms.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.tongji.tjlms.dto.UploadMaterialDto;
import edu.tongji.tjlms.model.MaterialEntity;
import edu.tongji.tjlms.service.material.MaterialService;
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

@ContextConfiguration(classes = {MaterialController.class})
@ExtendWith(SpringExtension.class)
class MaterialControllerTest {
    @Autowired
    private MaterialController materialController;

    @MockBean
    private MaterialService materialService;

    /**
     * Method under test: {@link MaterialController#uploadMaterial(UploadMaterialDto)}
     */
    @Test
    void testUploadMaterial() throws Exception {
        when(this.materialService.uploadMaterial((UploadMaterialDto) any())).thenReturn("Upload Material");

        UploadMaterialDto uploadMaterialDto = new UploadMaterialDto();
        uploadMaterialDto.setLabId(123);
        uploadMaterialDto.setLocation("Location");
        uploadMaterialDto.setName("Name");
        uploadMaterialDto.setUploader("Uploader");
        String content = (new ObjectMapper()).writeValueAsString(uploadMaterialDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/post/material")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.materialController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Upload Material"));
    }

    /**
     * Method under test: {@link MaterialController#deleteMaterial(Integer)}
     */
    @Test
    void testDeleteMaterial() throws Exception {
        when(this.materialService.deleteMaterial((Integer) any())).thenReturn("Delete Material");
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/delete/material/{materialId}",
                123);
        MockMvcBuilders.standaloneSetup(this.materialController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Delete Material"));
    }

    /**
     * Method under test: {@link MaterialController#deleteMaterial(Integer)}
     */
    @Test
    void testDeleteMaterial2() throws Exception {
        when(this.materialService.deleteMaterial((Integer) any())).thenReturn("Delete Material");
        MockHttpServletRequestBuilder postResult = MockMvcRequestBuilders.post("/api/delete/material/{materialId}", 123);
        postResult.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(this.materialController)
                .build()
                .perform(postResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Delete Material"));
    }

    /**
     * Method under test: {@link MaterialController#getAllMaterials(Integer, Integer)}
     */
    @Test
    void testGetAllMaterials() throws Exception {
        when(this.materialService.getAllMaterials((Integer) any(), (Integer) any())).thenReturn(new HashMap<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/get/all/material");
        MockHttpServletRequestBuilder paramResult = getResult.param("pageNum", String.valueOf(1));
        MockHttpServletRequestBuilder requestBuilder = paramResult.param("pageSize", String.valueOf(1));
        MockMvcBuilders.standaloneSetup(this.materialController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{}"));
    }

    /**
     * Method under test: {@link MaterialController#getAllMaterials(Integer, Integer)}
     */
    @Test
    void testGetAllMaterials2() throws Exception {
        when(this.materialService.getAllMaterials((Integer) any(), (Integer) any())).thenReturn(new HashMap<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/get/all/material");
        MockHttpServletRequestBuilder paramResult = getResult.param("pageNum", String.valueOf(0));
        MockHttpServletRequestBuilder requestBuilder = paramResult.param("pageSize", String.valueOf(1));
        MockMvcBuilders.standaloneSetup(this.materialController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{}"));
    }

    /**
     * Method under test: {@link MaterialController#getAllMaterials(Integer, Integer)}
     */
    @Test
    void testGetAllMaterials3() throws Exception {
        when(this.materialService.getAllMaterials((Integer) any(), (Integer) any())).thenReturn(new HashMap<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/get/all/material");
        getResult.contentType("https://example.org/example");
        MockHttpServletRequestBuilder paramResult = getResult.param("pageNum", String.valueOf(1));
        MockHttpServletRequestBuilder requestBuilder = paramResult.param("pageSize", String.valueOf(1));
        MockMvcBuilders.standaloneSetup(this.materialController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{}"));
    }

    /**
     * Method under test: {@link MaterialController#getAllMaterialByUploader(String, Integer, Integer)}
     */
    @Test
    void testGetAllMaterialByUploader() throws Exception {
        when(this.materialService.getAllByUploader((String) any(), (Integer) any(), (Integer) any()))
                .thenReturn(new PageImpl<>(new ArrayList<>()));
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/get/uploader/material/{teacherId}",
                "42");
        MockHttpServletRequestBuilder paramResult = getResult.param("pageNum", String.valueOf(1));
        MockHttpServletRequestBuilder requestBuilder = paramResult.param("pageSize", String.valueOf(1));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.materialController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("??????????"));
    }

    /**
     * Method under test: {@link MaterialController#getAllMaterialByUploader(String, Integer, Integer)}
     */
    @Test
    void testGetAllMaterialByUploader2() throws Exception {
        MaterialEntity materialEntity = new MaterialEntity();
        materialEntity.setId(1);
        materialEntity.setLabId(123);
        materialEntity.setLocation("?");
        materialEntity.setName("?");
        materialEntity.setUploadTime("?");
        materialEntity.setUploader("?");

        ArrayList<MaterialEntity> materialEntityList = new ArrayList<>();
        materialEntityList.add(materialEntity);
        PageImpl<MaterialEntity> pageImpl = new PageImpl<>(materialEntityList);
        when(this.materialService.getAllByUploader((String) any(), (Integer) any(), (Integer) any())).thenReturn(pageImpl);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/get/uploader/material/{teacherId}",
                "42");
        MockHttpServletRequestBuilder paramResult = getResult.param("pageNum", String.valueOf(1));
        MockHttpServletRequestBuilder requestBuilder = paramResult.param("pageSize", String.valueOf(1));
        MockMvcBuilders.standaloneSetup(this.materialController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"data\":{\"content\":[{\"id\":1,\"location\":\"?\",\"labId\":123,\"uploader\":\"?\",\"name\":\"?\",\"uploadTime\":\"?\"}],"
                                        + "\"pageable\":\"INSTANCE\",\"totalPages\":1,\"totalElements\":1,\"last\":true,\"number\":0,\"size\":1,\"sort\":{\"unsorted"
                                        + "\":true,\"sorted\":false,\"empty\":true},\"first\":true,\"numberOfElements\":1,\"empty\":false},\"count\":1}"));
    }

    /**
     * Method under test: {@link MaterialController#getAllMaterialByUploader(String, Integer, Integer)}
     */
    @Test
    void testGetAllMaterialByUploader3() throws Exception {
        when(this.materialService.getAllByUploader((String) any(), (Integer) any(), (Integer) any())).thenReturn(null);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/get/uploader/material/{teacherId}",
                "42");
        MockHttpServletRequestBuilder paramResult = getResult.param("pageNum", String.valueOf(1));
        MockHttpServletRequestBuilder requestBuilder = paramResult.param("pageSize", String.valueOf(1));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.materialController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400))
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("???????"));
    }

    /**
     * Method under test: {@link MaterialController#getAllMaterialByUploader(String, Integer, Integer)}
     */
    @Test
    void testGetAllMaterialByUploader4() throws Exception {
        when(this.materialService.getAllByUploader((String) any(), (Integer) any(), (Integer) any()))
                .thenReturn(new PageImpl<>(new ArrayList<>()));
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/get/uploader/material/{teacherId}",
                "42");
        MockHttpServletRequestBuilder paramResult = getResult.param("pageNum", String.valueOf(0));
        MockHttpServletRequestBuilder requestBuilder = paramResult.param("pageSize", String.valueOf(1));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.materialController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("??????????"));
    }

    /**
     * Method under test: {@link MaterialController#getAllMaterialByUploader(String, Integer, Integer)}
     */
    @Test
    void testGetAllMaterialByUploader5() throws Exception {
        when(this.materialService.getAllByUploader((String) any(), (Integer) any(), (Integer) any()))
                .thenReturn(new PageImpl<>(new ArrayList<>()));
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/get/uploader/material/{teacherId}",
                "42");
        getResult.contentType("https://example.org/example");
        MockHttpServletRequestBuilder paramResult = getResult.param("pageNum", String.valueOf(1));
        MockHttpServletRequestBuilder requestBuilder = paramResult.param("pageSize", String.valueOf(1));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.materialController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("??????????"));
    }

    /**
     * Method under test: {@link MaterialController#getAllMaterialsByLabId(Integer)}
     */
    @Test
    void testGetAllMaterialsByLabId() throws Exception {
        when(this.materialService.getAllByLabId((Integer) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/get/lab/material/{labId}", 123);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.materialController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("???????????"));
    }

    /**
     * Method under test: {@link MaterialController#getAllMaterialsByLabId(Integer)}
     */
    @Test
    void testGetAllMaterialsByLabId2() throws Exception {
        MaterialEntity materialEntity = new MaterialEntity();
        materialEntity.setId(1);
        materialEntity.setLabId(123);
        materialEntity.setLocation("?");
        materialEntity.setName("?");
        materialEntity.setUploadTime("?");
        materialEntity.setUploader("?");

        ArrayList<MaterialEntity> materialEntityList = new ArrayList<>();
        materialEntityList.add(materialEntity);
        when(this.materialService.getAllByLabId((Integer) any())).thenReturn(materialEntityList);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/get/lab/material/{labId}", 123);
        MockMvcBuilders.standaloneSetup(this.materialController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "[{\"id\":1,\"location\":\"?\",\"labId\":123,\"uploader\":\"?\",\"name\":\"?\",\"uploadTime\":\"?\"}]"));
    }

    /**
     * Method under test: {@link MaterialController#getAllMaterialsByLabId(Integer)}
     */
    @Test
    void testGetAllMaterialsByLabId3() throws Exception {
        when(this.materialService.getAllByLabId((Integer) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/get/lab/material/{labId}", 123);
        getResult.contentType("https://example.org/example");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.materialController)
                .build()
                .perform(getResult);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("???????????"));
    }

    /**
     * Method under test: {@link MaterialController#getAllMaterialsByLabId(Integer, Integer, Integer)}
     */
    @Test
    void testGetAllMaterialsByLabId4() throws Exception {
        when(this.materialService.getAllByLabId((Integer) any(), (Integer) any(), (Integer) any()))
                .thenReturn(new HashMap<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/get/lab/material/page/{labId}", 123);
        MockHttpServletRequestBuilder paramResult = getResult.param("pageNum", String.valueOf(1));
        MockHttpServletRequestBuilder requestBuilder = paramResult.param("pageSize", String.valueOf(1));
        MockMvcBuilders.standaloneSetup(this.materialController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{}"));
    }

    /**
     * Method under test: {@link MaterialController#getAllMaterialsByLabId(Integer, Integer, Integer)}
     */
    @Test
    void testGetAllMaterialsByLabId5() throws Exception {
        when(this.materialService.getAllByLabId((Integer) any(), (Integer) any(), (Integer) any()))
                .thenReturn(new HashMap<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/get/lab/material/page/{labId}", 123);
        MockHttpServletRequestBuilder paramResult = getResult.param("pageNum", String.valueOf(0));
        MockHttpServletRequestBuilder requestBuilder = paramResult.param("pageSize", String.valueOf(1));
        MockMvcBuilders.standaloneSetup(this.materialController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{}"));
    }

    /**
     * Method under test: {@link MaterialController#getAllMaterialsByLabId(Integer, Integer, Integer)}
     */
    @Test
    void testGetAllMaterialsByLabId6() throws Exception {
        when(this.materialService.getAllByLabId((Integer) any(), (Integer) any(), (Integer) any()))
                .thenReturn(new HashMap<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/get/lab/material/page/{labId}", 123);
        MockHttpServletRequestBuilder paramResult = getResult.param("pageNum", String.valueOf(-1));
        MockHttpServletRequestBuilder requestBuilder = paramResult.param("pageSize", String.valueOf(1));
        MockMvcBuilders.standaloneSetup(this.materialController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{}"));
    }

    /**
     * Method under test: {@link MaterialController#getAllMaterialsByLabId(Integer, Integer, Integer)}
     */
    @Test
    void testGetAllMaterialsByLabId7() throws Exception {
        when(this.materialService.getAllByLabId((Integer) any(), (Integer) any(), (Integer) any()))
                .thenReturn(new HashMap<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/get/lab/material/page/{labId}", 123);
        getResult.contentType("https://example.org/example");
        MockHttpServletRequestBuilder paramResult = getResult.param("pageNum", String.valueOf(1));
        MockHttpServletRequestBuilder requestBuilder = paramResult.param("pageSize", String.valueOf(1));
        MockMvcBuilders.standaloneSetup(this.materialController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{}"));
    }

    /**
     * Method under test: {@link MaterialController#getLatest()}
     */
    @Test
    void testGetLatest() throws Exception {
        when(this.materialService.getLatestFive()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/get/latest/material");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.materialController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("????????"));
    }

    /**
     * Method under test: {@link MaterialController#getLatest()}
     */
    @Test
    void testGetLatest2() throws Exception {
        MaterialEntity materialEntity = new MaterialEntity();
        materialEntity.setId(1);
        materialEntity.setLabId(123);
        materialEntity.setLocation("?");
        materialEntity.setName("?");
        materialEntity.setUploadTime("?");
        materialEntity.setUploader("?");

        ArrayList<MaterialEntity> materialEntityList = new ArrayList<>();
        materialEntityList.add(materialEntity);
        when(this.materialService.getLatestFive()).thenReturn(materialEntityList);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/get/latest/material");
        MockMvcBuilders.standaloneSetup(this.materialController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "[{\"id\":1,\"location\":\"?\",\"labId\":123,\"uploader\":\"?\",\"name\":\"?\",\"uploadTime\":\"?\"}]"));
    }

    /**
     * Method under test: {@link MaterialController#getLatest()}
     */
    @Test
    void testGetLatest3() throws Exception {
        when(this.materialService.getLatestFive()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/get/latest/material");
        getResult.contentType("https://example.org/example");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.materialController)
                .build()
                .perform(getResult);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("????????"));
    }
}

