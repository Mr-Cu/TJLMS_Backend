package edu.tongji.tjlms.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.tongji.tjlms.dto.FindBackPwdDto;
import edu.tongji.tjlms.dto.SafePwdDto;
import edu.tongji.tjlms.service.findbackpwd.FindBackPwdService;
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

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {FindBackPwdController.class})
@ExtendWith(SpringExtension.class)
class FindBackPwdControllerTest {
    @Autowired
    private FindBackPwdController findBackPwdController;

    @MockBean
    private FindBackPwdService findBackPwdService;

    /**
     * Method under test: {@link FindBackPwdController#findBackPwd(FindBackPwdDto)}
     */
    @Test
    void testFindBackPwd() throws Exception {
        when(this.findBackPwdService.resetPwd((FindBackPwdDto) any())).thenReturn("Reset Pwd");

        FindBackPwdDto findBackPwdDto = new FindBackPwdDto();
        findBackPwdDto.setId("42");
        findBackPwdDto.setNewPwd("New Pwd");
        findBackPwdDto.setUserType(1);
        String content = (new ObjectMapper()).writeValueAsString(findBackPwdDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/find/pwd")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.findBackPwdController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Reset Pwd"));
    }

    /**
     * Method under test: {@link FindBackPwdController#safePwd(SafePwdDto)}
     */
    @Test
    void testSafePwd() throws Exception {
        when(this.findBackPwdService.safePwd((SafePwdDto) any())).thenReturn("Safe Pwd");

        SafePwdDto safePwdDto = new SafePwdDto();
        safePwdDto.setId("42");
        safePwdDto.setPassword("iloveyou");
        String content = (new ObjectMapper()).writeValueAsString(safePwdDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/safe/pwd")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.findBackPwdController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Safe Pwd"));
    }
}

