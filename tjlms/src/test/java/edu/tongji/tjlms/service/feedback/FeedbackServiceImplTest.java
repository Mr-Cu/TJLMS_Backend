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
    void testMyFeedbackWithName() {
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

    /**
     * Method under test: {@link FeedbackServiceImpl#myFeedbackWithName(String, Integer, Integer)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testMyFeedbackWithName3() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at edu.tongji.tjlms.service.feedback.FeedbackServiceImpl.myFeedbackWithName(FeedbackServiceImpl.java:107)
        //   In order to prevent myFeedbackWithName(String, Integer, Integer)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   myFeedbackWithName(String, Integer, Integer).
        //   See https://diff.blue/R013 to resolve this issue.

        when(this.teacherRepository.findById((String) any())).thenReturn(null);

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
        this.feedbackServiceImpl.myFeedbackWithName("42", 10, 3);
    }

    /**
     * Method under test: {@link FeedbackServiceImpl#myFeedbackWithName(String, Integer, Integer)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testMyFeedbackWithName4() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at edu.tongji.tjlms.service.feedback.FeedbackServiceImpl.myFeedbackWithName(FeedbackServiceImpl.java:97)
        //   In order to prevent myFeedbackWithName(String, Integer, Integer)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   myFeedbackWithName(String, Integer, Integer).
        //   See https://diff.blue/R013 to resolve this issue.

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
        when(this.feedbackRepository.findAllByFeedbacker((String) any(), (org.springframework.data.domain.Pageable) any()))
                .thenReturn(null);
        FeedbackEntity feedbackEntity = mock(FeedbackEntity.class);
        when(feedbackEntity.getReplier()).thenReturn("Replier");
        doNothing().when(feedbackEntity).setAnonymous((Boolean) any());
        doNothing().when(feedbackEntity).setFbContent((String) any());
        doNothing().when(feedbackEntity).setFbTime((String) any());
        doNothing().when(feedbackEntity).setFbTitle((String) any());
        doNothing().when(feedbackEntity).setFeedbacker((String) any());
        doNothing().when(feedbackEntity).setId((Integer) any());
        doNothing().when(feedbackEntity).setIsReplied((Boolean) any());
        doNothing().when(feedbackEntity).setReplier((String) any());
        doNothing().when(feedbackEntity).setRpContent((String) any());
        doNothing().when(feedbackEntity).setRpTime((String) any());
        doNothing().when(feedbackEntity).setRpTitle((String) any());
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
        (new ArrayList<>()).add(feedbackEntity);
        this.feedbackServiceImpl.myFeedbackWithName("42", 10, 3);
    }

    /**
     * Method under test: {@link FeedbackServiceImpl#myFeedbackWithName(String, Integer, Integer)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testMyFeedbackWithName5() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.IllegalArgumentException: Page index must not be less than zero!
        //       at org.springframework.data.domain.AbstractPageRequest.<init>(AbstractPageRequest.java:44)
        //       at org.springframework.data.domain.PageRequest.<init>(PageRequest.java:45)
        //       at org.springframework.data.domain.PageRequest.of(PageRequest.java:72)
        //       at org.springframework.data.domain.PageRequest.of(PageRequest.java:60)
        //       at edu.tongji.tjlms.service.feedback.FeedbackServiceImpl.myFeedbackWithName(FeedbackServiceImpl.java:96)
        //   In order to prevent myFeedbackWithName(String, Integer, Integer)
        //   from throwing IllegalArgumentException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   myFeedbackWithName(String, Integer, Integer).
        //   See https://diff.blue/R013 to resolve this issue.

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
        FeedbackEntity feedbackEntity = mock(FeedbackEntity.class);
        when(feedbackEntity.getReplier()).thenReturn("Replier");
        doNothing().when(feedbackEntity).setAnonymous((Boolean) any());
        doNothing().when(feedbackEntity).setFbContent((String) any());
        doNothing().when(feedbackEntity).setFbTime((String) any());
        doNothing().when(feedbackEntity).setFbTitle((String) any());
        doNothing().when(feedbackEntity).setFeedbacker((String) any());
        doNothing().when(feedbackEntity).setId((Integer) any());
        doNothing().when(feedbackEntity).setIsReplied((Boolean) any());
        doNothing().when(feedbackEntity).setReplier((String) any());
        doNothing().when(feedbackEntity).setRpContent((String) any());
        doNothing().when(feedbackEntity).setRpTime((String) any());
        doNothing().when(feedbackEntity).setRpTitle((String) any());
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
        this.feedbackServiceImpl.myFeedbackWithName("42", -1, 3);
    }
}

