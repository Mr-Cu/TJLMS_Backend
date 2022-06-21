package edu.tongji.tjlms.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.ContentResultMatchers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {VerificationCodeCheckController.class})
@ExtendWith(SpringExtension.class)
class VerificationCodeCheckControllerTest {
    @Autowired
    private VerificationCodeCheckController verificationCodeCheckController;

    /**
     * Method under test: {@link VerificationCodeCheckController#checkCode(String)}
     */
    @Test
    void testCheckCode() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/post/check/code")
                .param("code", "foo");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.verificationCodeCheckController)
                .build()
                .perform(requestBuilder);
        ResultActions resultActions = actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400))
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
        ContentResultMatchers contentResult = MockMvcResultMatchers.content();
        resultActions.andExpect(contentResult.string(Boolean.FALSE.toString()));
    }

    /**
     * Method under test: {@link VerificationCodeCheckController#checkCode(String)}
     */
    @Test
    void testCheckCode2() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/post/check/code", "Uri Vars")
                .param("code", "foo");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.verificationCodeCheckController)
                .build()
                .perform(requestBuilder);
        ResultActions resultActions = actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400))
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
        ContentResultMatchers contentResult = MockMvcResultMatchers.content();
        resultActions.andExpect(contentResult.string(Boolean.FALSE.toString()));
    }

    /**
     * Method under test: {@link VerificationCodeCheckController#checkCode(String)}
     */
    @Test
    void testCheckCode3() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/post/check/code")
                .param("code", "");
        ResultActions resultActions = MockMvcBuilders.standaloneSetup(this.verificationCodeCheckController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
        ContentResultMatchers contentResult = MockMvcResultMatchers.content();
        resultActions.andExpect(contentResult.string(Boolean.TRUE.toString()));
    }
}

