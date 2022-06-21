package edu.tongji.tjlms.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.tongji.tjlms.dto.PreVerifyDto;
import edu.tongji.tjlms.dto.PreVerifyEmailDto;
import edu.tongji.tjlms.dto.PreVerifyExistsDto;
import edu.tongji.tjlms.service.preverify.PreVerifyService;
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
import org.springframework.test.web.servlet.result.ContentResultMatchers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {PreVerifyController.class})
@ExtendWith(SpringExtension.class)
class PreVerifyControllerTest {
    @Autowired
    private PreVerifyController preVerifyController;

    @MockBean
    private PreVerifyService preVerifyService;

    /**
     * Method under test: {@link PreVerifyController#emailCorrect(PreVerifyEmailDto)}
     */
    @Test
    void testEmailCorrect() throws Exception {
        when(this.preVerifyService.emailCorrect((PreVerifyEmailDto) any())).thenReturn(true);

        PreVerifyEmailDto preVerifyEmailDto = new PreVerifyEmailDto();
        preVerifyEmailDto.setEmail("jane.doe@example.org");
        preVerifyEmailDto.setId("42");
        preVerifyEmailDto.setUserType(1);
        String content = (new ObjectMapper()).writeValueAsString(preVerifyEmailDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/post/checkEmail")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions resultActions = MockMvcBuilders.standaloneSetup(this.preVerifyController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
        ContentResultMatchers contentResult = MockMvcResultMatchers.content();
        resultActions.andExpect(contentResult.string(Boolean.TRUE.toString()));
    }

    /**
     * Method under test: {@link PreVerifyController#emailCorrect(PreVerifyEmailDto)}
     */
    @Test
    void testEmailCorrect2() throws Exception {
        when(this.preVerifyService.emailCorrect((PreVerifyEmailDto) any())).thenReturn(false);

        PreVerifyEmailDto preVerifyEmailDto = new PreVerifyEmailDto();
        preVerifyEmailDto.setEmail("jane.doe@example.org");
        preVerifyEmailDto.setId("42");
        preVerifyEmailDto.setUserType(1);
        String content = (new ObjectMapper()).writeValueAsString(preVerifyEmailDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/post/checkEmail")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.preVerifyController)
                .build()
                .perform(requestBuilder);
        ResultActions resultActions = actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
        ContentResultMatchers contentResult = MockMvcResultMatchers.content();
        resultActions.andExpect(contentResult.string(Boolean.FALSE.toString()));
    }

    /**
     * Method under test: {@link PreVerifyController#idExists(PreVerifyExistsDto)}
     */
    @Test
    void testIdExists() throws Exception {
        when(this.preVerifyService.exists((PreVerifyExistsDto) any())).thenReturn(true);

        PreVerifyExistsDto preVerifyExistsDto = new PreVerifyExistsDto();
        preVerifyExistsDto.setId("42");
        preVerifyExistsDto.setUserType(1);
        String content = (new ObjectMapper()).writeValueAsString(preVerifyExistsDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/post/exists")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions resultActions = MockMvcBuilders.standaloneSetup(this.preVerifyController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
        ContentResultMatchers contentResult = MockMvcResultMatchers.content();
        resultActions.andExpect(contentResult.string(Boolean.TRUE.toString()));
    }

    /**
     * Method under test: {@link PreVerifyController#idExists(PreVerifyExistsDto)}
     */
    @Test
    void testIdExists2() throws Exception {
        when(this.preVerifyService.exists((PreVerifyExistsDto) any())).thenReturn(false);

        PreVerifyExistsDto preVerifyExistsDto = new PreVerifyExistsDto();
        preVerifyExistsDto.setId("42");
        preVerifyExistsDto.setUserType(1);
        String content = (new ObjectMapper()).writeValueAsString(preVerifyExistsDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/post/exists")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.preVerifyController)
                .build()
                .perform(requestBuilder);
        ResultActions resultActions = actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
        ContentResultMatchers contentResult = MockMvcResultMatchers.content();
        resultActions.andExpect(contentResult.string(Boolean.FALSE.toString()));
    }

    /**
     * Method under test: {@link PreVerifyController#preVerify(PreVerifyDto)}
     */
    @Test
    void testPreVerify() throws Exception {
        when(this.preVerifyService.exists((PreVerifyDto) any())).thenReturn(true);

        PreVerifyDto preVerifyDto = new PreVerifyDto();
        preVerifyDto.setId("42");
        preVerifyDto.setUserType(1);
        String content = (new ObjectMapper()).writeValueAsString(preVerifyDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/post/preVerify")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions resultActions = MockMvcBuilders.standaloneSetup(this.preVerifyController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
        ContentResultMatchers contentResult = MockMvcResultMatchers.content();
        resultActions.andExpect(contentResult.string(Boolean.TRUE.toString()));
    }

    /**
     * Method under test: {@link PreVerifyController#preVerify(PreVerifyDto)}
     */
    @Test
    void testPreVerify2() throws Exception {
        when(this.preVerifyService.exists((PreVerifyDto) any())).thenReturn(false);

        PreVerifyDto preVerifyDto = new PreVerifyDto();
        preVerifyDto.setId("42");
        preVerifyDto.setUserType(1);
        String content = (new ObjectMapper()).writeValueAsString(preVerifyDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/post/preVerify")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.preVerifyController)
                .build()
                .perform(requestBuilder);
        ResultActions resultActions = actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
        ContentResultMatchers contentResult = MockMvcResultMatchers.content();
        resultActions.andExpect(contentResult.string(Boolean.FALSE.toString()));
    }
}

