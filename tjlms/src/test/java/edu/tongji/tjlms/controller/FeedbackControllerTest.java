package edu.tongji.tjlms.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.tongji.tjlms.dto.FeedBackDto;
import edu.tongji.tjlms.dto.ReplyDto;
import edu.tongji.tjlms.model.FeedbackEntity;
import edu.tongji.tjlms.service.feedback.FeedbackService;
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

@ContextConfiguration(classes = {FeedbackController.class})
@ExtendWith(SpringExtension.class)
class FeedbackControllerTest {
    @Autowired
    private FeedbackController feedbackController;

    @MockBean
    private FeedbackService feedbackService;

    /**
     * Method under test: {@link FeedbackController#feedback(FeedBackDto)}
     */
    @Test
    void testFeedback() throws Exception {
        when(this.feedbackService.feedback((FeedBackDto) any())).thenReturn("Feedback");

        FeedBackDto feedBackDto = new FeedBackDto();
        feedBackDto.setContent("Not all who wander are lost");
        feedBackDto.setFeedbacker("Feedbacker");
        feedBackDto.setIsAnonymous(true);
        feedBackDto.setTitle("Dr");
        String content = (new ObjectMapper()).writeValueAsString(feedBackDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/post/feedback")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.feedbackController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Feedback"));
    }

    /**
     * Method under test: {@link FeedbackController#reply(ReplyDto)}
     */
    @Test
    void testReply() throws Exception {
        when(this.feedbackService.reply((ReplyDto) any())).thenReturn("Reply");

        ReplyDto replyDto = new ReplyDto();
        replyDto.setContent("Not all who wander are lost");
        replyDto.setId(1);
        replyDto.setReplier("Replier");
        replyDto.setTitle("Dr");
        String content = (new ObjectMapper()).writeValueAsString(replyDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/post/reply")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.feedbackController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Reply"));
    }

    /**
     * Method under test: {@link FeedbackController#getAllFeedback(Integer, Integer)}
     */
    @Test
    void testGetAllFeedback() throws Exception {
        when(this.feedbackService.getAllFeedback((Integer) any(), (Integer) any()))
                .thenReturn(new PageImpl<>(new ArrayList<>()));
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/get/all/feedback");
        MockHttpServletRequestBuilder paramResult = getResult.param("pageNum", String.valueOf(1));
        MockHttpServletRequestBuilder requestBuilder = paramResult.param("pageSize", String.valueOf(1));
        MockMvcBuilders.standaloneSetup(this.feedbackController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"content\":[],\"pageable\":\"INSTANCE\",\"last\":true,\"totalElements\":0,\"totalPages\":1,\"number\":0,\"size"
                                        + "\":0,\"sort\":{\"unsorted\":true,\"sorted\":false,\"empty\":true},\"first\":true,\"numberOfElements\":0,\"empty"
                                        + "\":true}"));
    }

    /**
     * Method under test: {@link FeedbackController#getAllFeedback(Integer, Integer)}
     */
    @Test
    void testGetAllFeedback2() throws Exception {
        when(this.feedbackService.getAllFeedback((Integer) any(), (Integer) any())).thenReturn(null);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/get/all/feedback");
        MockHttpServletRequestBuilder paramResult = getResult.param("pageNum", String.valueOf(1));
        MockHttpServletRequestBuilder requestBuilder = paramResult.param("pageSize", String.valueOf(1));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.feedbackController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("??????????"));
    }

    /**
     * Method under test: {@link FeedbackController#getAllFeedback(Integer, Integer)}
     */
    @Test
    void testGetAllFeedback3() throws Exception {
        when(this.feedbackService.getAllFeedback((Integer) any(), (Integer) any()))
                .thenReturn(new PageImpl<>(new ArrayList<>()));
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/get/all/feedback");
        getResult.contentType("https://example.org/example");
        MockHttpServletRequestBuilder paramResult = getResult.param("pageNum", String.valueOf(1));
        MockHttpServletRequestBuilder requestBuilder = paramResult.param("pageSize", String.valueOf(1));
        MockMvcBuilders.standaloneSetup(this.feedbackController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"content\":[],\"pageable\":\"INSTANCE\",\"last\":true,\"totalElements\":0,\"totalPages\":1,\"number\":0,\"size"
                                        + "\":0,\"sort\":{\"unsorted\":true,\"sorted\":false,\"empty\":true},\"first\":true,\"numberOfElements\":0,\"empty"
                                        + "\":true}"));
    }

    /**
     * Method under test: {@link FeedbackController#getFeedbackById(Integer)}
     */
    @Test
    void testGetFeedbackById() throws Exception {
        FeedbackEntity feedbackEntity = new FeedbackEntity();
        feedbackEntity.setAnonymous(true);
        feedbackEntity.setFbContent("Not all who wander are lost");
        feedbackEntity.setFbTime("Fb Time");
        feedbackEntity.setFbTitle("Dr");
        feedbackEntity.setFeedbacker("Feedbacker");
        feedbackEntity.setId(1);
        feedbackEntity.setIsReplied(true);
        feedbackEntity.setReplier("Replier");
        feedbackEntity.setRpContent("Not all who wander are lost");
        feedbackEntity.setRpTime("Rp Time");
        feedbackEntity.setRpTitle("Dr");
        when(this.feedbackService.getFeedbackById((Integer) any())).thenReturn(feedbackEntity);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/get/feedback/{feedbackId}", 123);
        MockMvcBuilders.standaloneSetup(this.feedbackController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"feedbacker\":\"Feedbacker\",\"fbTitle\":\"Dr\",\"fbContent\":\"Not all who wander are lost\",\"fbTime\":\"Fb"
                                        + " Time\",\"replier\":\"Replier\",\"rpTitle\":\"Dr\",\"rpContent\":\"Not all who wander are lost\",\"rpTime\":\"Rp"
                                        + " Time\",\"isReplied\":true,\"anonymous\":true}"));
    }

    /**
     * Method under test: {@link FeedbackController#getFeedbackById(Integer)}
     */
    @Test
    void testGetFeedbackById2() throws Exception {
        FeedbackEntity feedbackEntity = new FeedbackEntity();
        feedbackEntity.setAnonymous(true);
        feedbackEntity.setFbContent("Not all who wander are lost");
        feedbackEntity.setFbTime("Fb Time");
        feedbackEntity.setFbTitle("Dr");
        feedbackEntity.setFeedbacker("Feedbacker");
        feedbackEntity.setId(1);
        feedbackEntity.setIsReplied(true);
        feedbackEntity.setReplier("Replier");
        feedbackEntity.setRpContent("Not all who wander are lost");
        feedbackEntity.setRpTime("Rp Time");
        feedbackEntity.setRpTitle("Dr");
        when(this.feedbackService.getFeedbackById((Integer) any())).thenReturn(feedbackEntity);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/get/feedback/{feedbackId}", 123);
        getResult.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(this.feedbackController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"feedbacker\":\"Feedbacker\",\"fbTitle\":\"Dr\",\"fbContent\":\"Not all who wander are lost\",\"fbTime\":\"Fb"
                                        + " Time\",\"replier\":\"Replier\",\"rpTitle\":\"Dr\",\"rpContent\":\"Not all who wander are lost\",\"rpTime\":\"Rp"
                                        + " Time\",\"isReplied\":true,\"anonymous\":true}"));
    }

    /**
     * Method under test: {@link FeedbackController#getMyFeedback(String, Integer, Integer)}
     */
    @Test
    void testGetMyFeedback() throws Exception {
        when(this.feedbackService.myFeedbackWithName((String) any(), (Integer) any(), (Integer) any()))
                .thenReturn(new HashMap<>());
        MockHttpServletRequestBuilder paramResult = MockMvcRequestBuilders.get("/api/get/my/feedback").param("id", "foo");
        MockHttpServletRequestBuilder paramResult1 = paramResult.param("pageNum", String.valueOf(1));
        MockHttpServletRequestBuilder requestBuilder = paramResult1.param("pageSize", String.valueOf(1));
        MockMvcBuilders.standaloneSetup(this.feedbackController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{}"));
    }

    /**
     * Method under test: {@link FeedbackController#getMyFeedback(String, Integer, Integer)}
     */
    @Test
    void testGetMyFeedback2() throws Exception {
        when(this.feedbackService.myFeedbackWithName((String) any(), (Integer) any(), (Integer) any()))
                .thenReturn(new HashMap<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/get/my/feedback");
        getResult.contentType("https://example.org/example");
        MockHttpServletRequestBuilder paramResult = getResult.param("id", "foo");
        MockHttpServletRequestBuilder paramResult1 = paramResult.param("pageNum", String.valueOf(1));
        MockHttpServletRequestBuilder requestBuilder = paramResult1.param("pageSize", String.valueOf(1));
        MockMvcBuilders.standaloneSetup(this.feedbackController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{}"));
    }

    /**
     * Method under test: {@link FeedbackController#getMyReply(String, Integer, Integer)}
     */
    @Test
    void testGetMyReply() throws Exception {
        when(this.feedbackService.myReply((String) any(), (Integer) any(), (Integer) any()))
                .thenReturn(new PageImpl<>(new ArrayList<>()));
        MockHttpServletRequestBuilder paramResult = MockMvcRequestBuilders.get("/api/get/my/reply").param("id", "foo");
        MockHttpServletRequestBuilder paramResult1 = paramResult.param("pageNum", String.valueOf(1));
        MockHttpServletRequestBuilder requestBuilder = paramResult1.param("pageSize", String.valueOf(1));
        MockMvcBuilders.standaloneSetup(this.feedbackController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"content\":[],\"pageable\":\"INSTANCE\",\"last\":true,\"totalElements\":0,\"totalPages\":1,\"number\":0,\"size"
                                        + "\":0,\"sort\":{\"unsorted\":true,\"sorted\":false,\"empty\":true},\"first\":true,\"numberOfElements\":0,\"empty"
                                        + "\":true}"));
    }

    /**
     * Method under test: {@link FeedbackController#getMyReply(String, Integer, Integer)}
     */
    @Test
    void testGetMyReply2() throws Exception {
        when(this.feedbackService.myReply((String) any(), (Integer) any(), (Integer) any())).thenReturn(null);
        MockHttpServletRequestBuilder paramResult = MockMvcRequestBuilders.get("/api/get/my/reply").param("id", "foo");
        MockHttpServletRequestBuilder paramResult1 = paramResult.param("pageNum", String.valueOf(1));
        MockHttpServletRequestBuilder requestBuilder = paramResult1.param("pageSize", String.valueOf(1));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.feedbackController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("?????????"));
    }

    /**
     * Method under test: {@link FeedbackController#getMyReply(String, Integer, Integer)}
     */
    @Test
    void testGetMyReply3() throws Exception {
        when(this.feedbackService.myReply((String) any(), (Integer) any(), (Integer) any()))
                .thenReturn(new PageImpl<>(new ArrayList<>()));
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/get/my/reply");
        getResult.contentType("https://example.org/example");
        MockHttpServletRequestBuilder paramResult = getResult.param("id", "foo");
        MockHttpServletRequestBuilder paramResult1 = paramResult.param("pageNum", String.valueOf(1));
        MockHttpServletRequestBuilder requestBuilder = paramResult1.param("pageSize", String.valueOf(1));
        MockMvcBuilders.standaloneSetup(this.feedbackController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"content\":[],\"pageable\":\"INSTANCE\",\"last\":true,\"totalElements\":0,\"totalPages\":1,\"number\":0,\"size"
                                        + "\":0,\"sort\":{\"unsorted\":true,\"sorted\":false,\"empty\":true},\"first\":true,\"numberOfElements\":0,\"empty"
                                        + "\":true}"));
    }
}

