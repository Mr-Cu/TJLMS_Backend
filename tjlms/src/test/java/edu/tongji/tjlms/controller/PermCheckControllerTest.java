package edu.tongji.tjlms.controller;

import edu.tongji.tjlms.service.perm.PermService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
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

@ContextConfiguration(classes = {PermCheckController.class})
@ExtendWith(SpringExtension.class)
class PermCheckControllerTest {
    @Autowired
    private PermCheckController permCheckController;

    @MockBean
    private PermService permService;

    /**
     * Method under test: {@link PermCheckController#checkDdl(Integer)}
     */
    @Test
    void testCheckDdl() throws Exception {
        when(this.permService.earlierThanDdl((Integer) any())).thenReturn(true);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/check/ddl/{teacherId}", 123);
        ResultActions resultActions = MockMvcBuilders.standaloneSetup(this.permCheckController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
        ContentResultMatchers contentResult = MockMvcResultMatchers.content();
        resultActions.andExpect(contentResult.string(Boolean.TRUE.toString()));
    }

    /**
     * Method under test: {@link PermCheckController#checkDdl(Integer)}
     */
    @Test
    void testCheckDdl2() throws Exception {
        when(this.permService.earlierThanDdl((Integer) any())).thenReturn(false);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/check/ddl/{teacherId}", 123);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.permCheckController)
                .build()
                .perform(requestBuilder);
        ResultActions resultActions = actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400))
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
        ContentResultMatchers contentResult = MockMvcResultMatchers.content();
        resultActions.andExpect(contentResult.string(Boolean.FALSE.toString()));
    }

    /**
     * Method under test: {@link PermCheckController#checkDdl(Integer)}
     */
    @Test
    void testCheckDdl3() throws Exception {
        when(this.permService.earlierThanDdl((Integer) any())).thenReturn(true);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/check/ddl/{teacherId}", 123);
        getResult.contentType("https://example.org/example");
        ResultActions resultActions = MockMvcBuilders.standaloneSetup(this.permCheckController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
        ContentResultMatchers contentResult = MockMvcResultMatchers.content();
        resultActions.andExpect(contentResult.string(Boolean.TRUE.toString()));
    }

    /**
     * Method under test: {@link PermCheckController#checkGrade(String)}
     */
    @Test
    void testCheckGrade() throws Exception {
        when(this.permService.canGrade((String) any())).thenReturn(true);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/check/grade/{teacherId}", "42");
        ResultActions resultActions = MockMvcBuilders.standaloneSetup(this.permCheckController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
        ContentResultMatchers contentResult = MockMvcResultMatchers.content();
        resultActions.andExpect(contentResult.string(Boolean.TRUE.toString()));
    }

    /**
     * Method under test: {@link PermCheckController#checkGrade(String)}
     */
    @Test
    void testCheckGrade2() throws Exception {
        when(this.permService.canGrade((String) any())).thenReturn(false);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/check/grade/{teacherId}", "42");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.permCheckController)
                .build()
                .perform(requestBuilder);
        ResultActions resultActions = actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400))
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
        ContentResultMatchers contentResult = MockMvcResultMatchers.content();
        resultActions.andExpect(contentResult.string(Boolean.FALSE.toString()));
    }

    /**
     * Method under test: {@link PermCheckController#checkGrade(String)}
     */
    @Test
    void testCheckGrade3() throws Exception {
        when(this.permService.canGrade((String) any())).thenReturn(true);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/check/grade/{teacherId}", "42");
        getResult.contentType("https://example.org/example");
        ResultActions resultActions = MockMvcBuilders.standaloneSetup(this.permCheckController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
        ContentResultMatchers contentResult = MockMvcResultMatchers.content();
        resultActions.andExpect(contentResult.string(Boolean.TRUE.toString()));
    }

    /**
     * Method under test: {@link PermCheckController#checkNotice(String)}
     */
    @Test
    void testCheckNotice() throws Exception {
        when(this.permService.isResp((String) any())).thenReturn(true);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/check/notice/{teacherId}", "42");
        ResultActions resultActions = MockMvcBuilders.standaloneSetup(this.permCheckController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
        ContentResultMatchers contentResult = MockMvcResultMatchers.content();
        resultActions.andExpect(contentResult.string(Boolean.TRUE.toString()));
    }

    /**
     * Method under test: {@link PermCheckController#checkNotice(String)}
     */
    @Test
    void testCheckNotice2() throws Exception {
        when(this.permService.isResp((String) any())).thenReturn(false);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/check/notice/{teacherId}", "42");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.permCheckController)
                .build()
                .perform(requestBuilder);
        ResultActions resultActions = actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400))
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
        ContentResultMatchers contentResult = MockMvcResultMatchers.content();
        resultActions.andExpect(contentResult.string(Boolean.FALSE.toString()));
    }

    /**
     * Method under test: {@link PermCheckController#checkNotice(String)}
     */
    @Test
    void testCheckNotice3() throws Exception {
        when(this.permService.isResp((String) any())).thenReturn(true);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/check/notice/{teacherId}", "42");
        getResult.contentType("https://example.org/example");
        ResultActions resultActions = MockMvcBuilders.standaloneSetup(this.permCheckController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
        ContentResultMatchers contentResult = MockMvcResultMatchers.content();
        resultActions.andExpect(contentResult.string(Boolean.TRUE.toString()));
    }

    /**
     * Method under test: {@link PermCheckController#checkRelease(String)}
     */
    @Test
    void testCheckRelease() throws Exception {
        when(this.permService.canRelease((String) any())).thenReturn(true);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/check/release/{teacherId}", "42");
        ResultActions resultActions = MockMvcBuilders.standaloneSetup(this.permCheckController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
        ContentResultMatchers contentResult = MockMvcResultMatchers.content();
        resultActions.andExpect(contentResult.string(Boolean.TRUE.toString()));
    }

    /**
     * Method under test: {@link PermCheckController#checkRelease(String)}
     */
    @Test
    void testCheckRelease2() throws Exception {
        when(this.permService.canRelease((String) any())).thenReturn(false);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/check/release/{teacherId}", "42");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.permCheckController)
                .build()
                .perform(requestBuilder);
        ResultActions resultActions = actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400))
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
        ContentResultMatchers contentResult = MockMvcResultMatchers.content();
        resultActions.andExpect(contentResult.string(Boolean.FALSE.toString()));
    }

    /**
     * Method under test: {@link PermCheckController#checkRelease(String)}
     */
    @Test
    void testCheckRelease3() throws Exception {
        when(this.permService.canRelease((String) any())).thenReturn(true);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/check/release/{teacherId}", "42");
        getResult.contentType("https://example.org/example");
        ResultActions resultActions = MockMvcBuilders.standaloneSetup(this.permCheckController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
        ContentResultMatchers contentResult = MockMvcResultMatchers.content();
        resultActions.andExpect(contentResult.string(Boolean.TRUE.toString()));
    }
}

