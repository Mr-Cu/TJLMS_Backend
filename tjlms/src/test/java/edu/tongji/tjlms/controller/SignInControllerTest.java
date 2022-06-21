package edu.tongji.tjlms.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.tongji.tjlms.dto.SignInDto;
import edu.tongji.tjlms.service.signin.SignInService;
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

@ContextConfiguration(classes = {SignInController.class})
@ExtendWith(SpringExtension.class)
class SignInControllerTest {
    @Autowired
    private SignInController signInController;

    @MockBean
    private SignInService signInService;

    /**
     * Method under test: {@link SignInController#signIn(SignInDto)}
     */
    @Test
    void testSignIn() throws Exception {
        when(this.signInService.signIn((SignInDto) any())).thenReturn("Sign In");

        SignInDto signInDto = new SignInDto();
        signInDto.setEmailAddress("42 Main St");
        signInDto.setId("42");
        signInDto.setPassword("iloveyou");
        signInDto.setUserType(1);
        String content = (new ObjectMapper()).writeValueAsString(signInDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/post/sign/in")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.signInController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400))
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Sign In"));
    }

    /**
     * Method under test: {@link SignInController#signIn(SignInDto)}
     */
    @Test
    void testSignIn2() throws Exception {
        when(this.signInService.signIn((SignInDto) any())).thenReturn("注册成功");

        SignInDto signInDto = new SignInDto();
        signInDto.setEmailAddress("42 Main St");
        signInDto.setId("42");
        signInDto.setPassword("iloveyou");
        signInDto.setUserType(1);
        String content = (new ObjectMapper()).writeValueAsString(signInDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/post/sign/in")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.signInController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("????"));
    }
}

