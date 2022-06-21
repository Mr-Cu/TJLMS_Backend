package edu.tongji.tjlms.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.tongji.tjlms.dto.AddFileDto;
import edu.tongji.tjlms.service.minio.MinIOService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {MinIOController.class})
@ExtendWith(SpringExtension.class)
class MinIOControllerTest {
    @Autowired
    private MinIOController minIOController;

    @MockBean
    private MinIOService minIOService;

    /**
     * Method under test: {@link MinIOController#uploadFile(org.springframework.web.multipart.MultipartFile)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testUploadFile() throws IOException {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at edu.tongji.tjlms.controller.MinIOController.uploadFile(MinIOController.java:26)
        //   In order to prevent uploadFile(MultipartFile)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   uploadFile(MultipartFile).
        //   See https://diff.blue/R013 to resolve this issue.

        MinIOController minIOController = new MinIOController();
        minIOController.uploadFile(new MockMultipartFile("Name", new ByteArrayInputStream("AAAAAAAA".getBytes("UTF-8"))));
    }

    /**
     * Method under test: {@link MinIOController#getFile(String)}
     */
    @Test
    void testGetFile() throws Exception {
        when(this.minIOService.getFile((String) any())).thenReturn("AAAAAAAA".getBytes("UTF-8"));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/minIO/get/{fileName}", "foo.txt");
        MockMvcBuilders.standaloneSetup(this.minIOController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/octet-stream"))
                .andExpect(MockMvcResultMatchers.content().string("AAAAAAAA"));
    }

    /**
     * Method under test: {@link MinIOController#getFile(String)}
     */
    @Test
    void testGetFile2() throws Exception {
        when(this.minIOService.getFile((String) any())).thenReturn("AAAAAAAA".getBytes("UTF-8"));
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/minIO/get/{fileName}", "foo.txt");
        getResult.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(this.minIOController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/octet-stream"))
                .andExpect(MockMvcResultMatchers.content().string("AAAAAAAA"));
    }

    /**
     * Method under test: {@link MinIOController#removeFile(String)}
     */
    @Test
    void testRemoveFile() throws Exception {
        when(this.minIOService.removeFile((String) any())).thenReturn("Remove File");
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/minIO/remove/{fileName}",
                "foo.txt");
        MockMvcBuilders.standaloneSetup(this.minIOController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Remove File"));
    }

    /**
     * Method under test: {@link MinIOController#removeFile(String)}
     */
    @Test
    void testRemoveFile2() throws Exception {
        when(this.minIOService.removeFile((String) any())).thenReturn("Remove File");
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/minIO/remove/{fileName}", "foo.txt");
        getResult.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(this.minIOController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Remove File"));
    }

    /**
     * Method under test: {@link MinIOController#addToFile(AddFileDto)}
     */
    @Test
    void testAddToFile() throws Exception {
        doNothing().when(this.minIOService).addToFile((AddFileDto) any());

        AddFileDto addFileDto = new AddFileDto();
        addFileDto.setExtendFile("Extend File");
        addFileDto.setFileName("foo.txt");
        String content = (new ObjectMapper()).writeValueAsString(addFileDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/minIO/addToFile")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.minIOController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test: {@link MinIOController#replaceFile(AddFileDto)}
     */
    @Test
    void testReplaceFile() throws Exception {
        doNothing().when(this.minIOService).ReplaceFile((AddFileDto) any());

        AddFileDto addFileDto = new AddFileDto();
        addFileDto.setExtendFile("Extend File");
        addFileDto.setFileName("foo.txt");
        String content = (new ObjectMapper()).writeValueAsString(addFileDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/minIO/replaceFile")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.minIOController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}

