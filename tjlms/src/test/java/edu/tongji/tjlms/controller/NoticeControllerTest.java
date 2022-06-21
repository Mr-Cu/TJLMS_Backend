package edu.tongji.tjlms.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.tongji.tjlms.dto.GetNoticeDto;
import edu.tongji.tjlms.dto.PostNoticeDto;
import edu.tongji.tjlms.model.NoticeEntity;
import edu.tongji.tjlms.service.notice.NoticeService;
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
import java.util.HashMap;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {NoticeController.class})
@ExtendWith(SpringExtension.class)
class NoticeControllerTest {
    @Autowired
    private NoticeController noticeController;

    @MockBean
    private NoticeService noticeService;

    /**
     * Method under test: {@link NoticeController#getAllTitles(Integer, Integer)}
     */
    @Test
    void testGetAllTitles() throws Exception {
        when(this.noticeService.getAllTitles((Integer) any(), (Integer) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/get/titles");
        MockHttpServletRequestBuilder paramResult = getResult.param("pageNum", String.valueOf(1));
        MockHttpServletRequestBuilder requestBuilder = paramResult.param("pageSize", String.valueOf(1));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.noticeController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("????"));
    }

    /**
     * Method under test: {@link NoticeController#getAllTitles(Integer, Integer)}
     */
    @Test
    void testGetAllTitles2() throws Exception {
        ArrayList<GetNoticeDto> getNoticeDtoList = new ArrayList<>();
        getNoticeDtoList.add(new GetNoticeDto(1, "Dr", "?", "?"));
        when(this.noticeService.getAllTitles((Integer) any(), (Integer) any())).thenReturn(getNoticeDtoList);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/get/titles");
        MockHttpServletRequestBuilder paramResult = getResult.param("pageNum", String.valueOf(1));
        MockHttpServletRequestBuilder requestBuilder = paramResult.param("pageSize", String.valueOf(1));
        MockMvcBuilders.standaloneSetup(this.noticeController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("[{\"id\":1,\"title\":\"Dr\",\"time\":\"?\",\"teacherName\":\"?\"}]"));
    }

    /**
     * Method under test: {@link NoticeController#getAllTitles(Integer, Integer)}
     */
    @Test
    void testGetAllTitles3() throws Exception {
        when(this.noticeService.getAllTitles((Integer) any(), (Integer) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/get/titles");
        getResult.contentType("https://example.org/example");
        MockHttpServletRequestBuilder paramResult = getResult.param("pageNum", String.valueOf(1));
        MockHttpServletRequestBuilder requestBuilder = paramResult.param("pageSize", String.valueOf(1));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.noticeController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("????"));
    }

    /**
     * Method under test: {@link NoticeController#getNoticeByReleaser(String)}
     */
    @Test
    void testGetNoticeByReleaser() throws Exception {
        when(this.noticeService.getNoticeByReleaser((String) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/get/notice/releaser/{teacherId}",
                "42");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.noticeController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("????"));
    }

    /**
     * Method under test: {@link NoticeController#getNoticeByReleaser(String)}
     */
    @Test
    void testGetNoticeByReleaser2() throws Exception {
        NoticeEntity noticeEntity = new NoticeEntity();
        noticeEntity.setContent("Not all who wander are lost");
        noticeEntity.setId(1);
        noticeEntity.setReleaseTime("1.0.2");
        noticeEntity.setReleaser("1.0.2");
        noticeEntity.setTitle("Dr");

        ArrayList<NoticeEntity> noticeEntityList = new ArrayList<>();
        noticeEntityList.add(noticeEntity);
        when(this.noticeService.getNoticeByReleaser((String) any())).thenReturn(noticeEntityList);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/get/notice/releaser/{teacherId}",
                "42");
        MockMvcBuilders.standaloneSetup(this.noticeController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "[{\"id\":1,\"releaser\":\"1.0.2\",\"title\":\"Dr\",\"content\":\"Not all who wander are lost\",\"releaseTime\""
                                        + ":\"1.0.2\"}]"));
    }

    /**
     * Method under test: {@link NoticeController#getNoticeByReleaser(String)}
     */
    @Test
    void testGetNoticeByReleaser3() throws Exception {
        when(this.noticeService.getNoticeByReleaser((String) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/get/notice/releaser/{teacherId}", "42");
        getResult.contentType("https://example.org/example");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.noticeController)
                .build()
                .perform(getResult);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("????"));
    }

    /**
     * Method under test: {@link NoticeController#postNotice(PostNoticeDto)}
     */
    @Test
    void testPostNotice() throws Exception {
        when(this.noticeService.postNotice((PostNoticeDto) any())).thenReturn("Post Notice");

        PostNoticeDto postNoticeDto = new PostNoticeDto();
        postNoticeDto.setContent("Not all who wander are lost");
        postNoticeDto.setReleaser("1.0.2");
        postNoticeDto.setTitle("Dr");
        String content = (new ObjectMapper()).writeValueAsString(postNoticeDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/post/notice")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.noticeController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Post Notice"));
    }

    /**
     * Method under test: {@link NoticeController#deleteNotice(Integer)}
     */
    @Test
    void testDeleteNotice() throws Exception {
        when(this.noticeService.deleteNotice((Integer) any())).thenReturn("Delete Notice");
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/delete/notice/{noticeId}", 123);
        MockMvcBuilders.standaloneSetup(this.noticeController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Delete Notice"));
    }

    /**
     * Method under test: {@link NoticeController#deleteNotice(Integer)}
     */
    @Test
    void testDeleteNotice2() throws Exception {
        when(this.noticeService.deleteNotice((Integer) any())).thenReturn("Delete Notice");
        MockHttpServletRequestBuilder postResult = MockMvcRequestBuilders.post("/api/delete/notice/{noticeId}", 123);
        postResult.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(this.noticeController)
                .build()
                .perform(postResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Delete Notice"));
    }

    /**
     * Method under test: {@link NoticeController#getNoticeById(Integer)}
     */
    @Test
    void testGetNoticeById() throws Exception {
        when(this.noticeService.geyNoticeWithNameById((Integer) any())).thenReturn(new HashMap<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/get/notice/id/{noticeId}", 123);
        MockMvcBuilders.standaloneSetup(this.noticeController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{}"));
    }

    /**
     * Method under test: {@link NoticeController#getNoticeById(Integer)}
     */
    @Test
    void testGetNoticeById2() throws Exception {
        when(this.noticeService.geyNoticeWithNameById((Integer) any())).thenReturn(new HashMap<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/get/notice/id/{noticeId}", 123);
        getResult.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(this.noticeController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{}"));
    }
}

