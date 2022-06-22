package edu.tongji.tjlms.service.feedback;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import edu.tongji.tjlms.model.FeedbackEntity;
import edu.tongji.tjlms.model.TeacherEntity;
import edu.tongji.tjlms.repository.FeedbackRepository;
import edu.tongji.tjlms.repository.StudentRepository;
import edu.tongji.tjlms.repository.TeacherRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {FeedbackServiceImpl.class})
@ExtendWith(SpringExtension.class)
class FeedbackServiceImplTest {
    @MockBean
    private FeedbackRepository feedbackRepository;

    @Autowired
    private FeedbackServiceImpl feedbackServiceImpl;

    @MockBean
    private StudentRepository studentRepository;

    @MockBean(name = "TeacherRepository")
    private TeacherRepository teacherRepository;

    /**
     * Method under test: {@link FeedbackServiceImpl#myFeedbackWithName(String, Integer, Integer)}
     */
    @Test
    void testMyFeedbackWithName1() {
        when(this.feedbackRepository.findAllByFeedbacker((String) any(), (org.springframework.data.domain.Pageable) any()))
                .thenReturn(new PageImpl<>(new ArrayList<>()));
        assertNull(this.feedbackServiceImpl.myFeedbackWithName("42", 10, 3));
        verify(this.feedbackRepository).findAllByFeedbacker((String) any(),
                (org.springframework.data.domain.Pageable) any());
    }

    /**
     * Method under test: {@link FeedbackServiceImpl#myFeedbackWithName(String, Integer, Integer)}
     */
    @Test
    void testMyFeedbackWithName2() {
        TeacherEntity teacherEntity = new TeacherEntity();
        teacherEntity.setEmailAddr("42 Main St");
        teacherEntity.setGrade(true);
        teacherEntity.setId("42");
        teacherEntity.setName("Name");
        teacherEntity.setPassword("iloveyou");
        teacherEntity.setReleaseLab(true);
        teacherEntity.setType(1);
        teacherEntity.setVerified(true);
        Optional<TeacherEntity> ofResult = Optional.of(teacherEntity);
        when(this.teacherRepository.findById((String) any())).thenReturn(ofResult);

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

        ArrayList<FeedbackEntity> feedbackEntityList = new ArrayList<>();
        feedbackEntityList.add(feedbackEntity);
        PageImpl<FeedbackEntity> pageImpl = new PageImpl<>(feedbackEntityList);
        when(this.feedbackRepository.findAllByFeedbacker((String) any(), (org.springframework.data.domain.Pageable) any()))
                .thenReturn(pageImpl);
        Map<String, Object> actualMyFeedbackWithNameResult = this.feedbackServiceImpl.myFeedbackWithName("42", 10, 3);
        assertEquals(2, actualMyFeedbackWithNameResult.size());
        Object getResult = actualMyFeedbackWithNameResult.get("names");
        assertEquals(1, ((Collection<String>) getResult).size());
        assertEquals("Replier Name", ((List<String>) getResult).get(0));
        verify(this.teacherRepository).findById((String) any());
        verify(this.feedbackRepository).findAllByFeedbacker((String) any(),
                (org.springframework.data.domain.Pageable) any());
    }

}

