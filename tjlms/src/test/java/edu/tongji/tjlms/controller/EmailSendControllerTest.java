package edu.tongji.tjlms.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.tongji.tjlms.dto.EmailDto;
import edu.tongji.tjlms.service.email.EmailSendService;
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

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {EmailSendController.class})
@ExtendWith(SpringExtension.class)
class EmailSendControllerTest {
    @Autowired
    private EmailSendController emailSendController;

    @MockBean
    private EmailSendService emailSendService;

    /**
     * Method under test: {@link EmailSendController#sendEmail(EmailDto)}
     */
    @Test
    void testSendEmail() throws Exception {
        when(this.emailSendService.sendEmail((EmailDto) any())).thenReturn("jane.doe@example.org");

        EmailDto emailDto = new EmailDto();
        emailDto.setEmailAddress("42 Main St");
        emailDto.setId("42");
        emailDto.setUserType(1);
        String content = (new ObjectMapper()).writeValueAsString(emailDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/post/verify")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.emailSendController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400))
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("jane.doe@example.org"));
    }

    /**
     * Method under test: {@link EmailSendController#sendEmail(EmailDto)}
     */
    @Test
    void testSendEmail2() throws Exception {
        when(this.emailSendService.sendEmail((EmailDto) any())).thenReturn("发送成功");

        EmailDto emailDto = new EmailDto();
        emailDto.setEmailAddress("42 Main St");
        emailDto.setId("42");
        emailDto.setUserType(1);
        String content = (new ObjectMapper()).writeValueAsString(emailDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/post/verify")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.emailSendController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("????"));
    }

    /**
     * Method under test: {@link EmailSendController#sendEmail(EmailDto)}
     */
    @Test
    void testSendEmail3() throws Exception {
        when(this.emailSendService.sendEmail((EmailDto) any())).thenReturn("学工号错误");

        EmailDto emailDto = new EmailDto();
        emailDto.setEmailAddress("42 Main St");
        emailDto.setId("42");
        emailDto.setUserType(1);
        String content = (new ObjectMapper()).writeValueAsString(emailDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/post/verify")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.emailSendController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("?????"));
    }
}

