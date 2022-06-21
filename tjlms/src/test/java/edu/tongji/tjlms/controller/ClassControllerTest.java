package edu.tongji.tjlms.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.tongji.tjlms.dto.InsertClassDto;
import edu.tongji.tjlms.dto.InsertStudentDto;
import edu.tongji.tjlms.dto.InsertStudentsDto;
import edu.tongji.tjlms.model.ClassEntity;
import edu.tongji.tjlms.model.TakesEntity;
import edu.tongji.tjlms.model.TeacherEntity;
import edu.tongji.tjlms.service.clazz.ClassService;
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

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {ClassController.class})
@ExtendWith(SpringExtension.class)
class ClassControllerTest {
    @Autowired
    private ClassController classController;

    @MockBean
    private ClassService classService;

    /**
     * Method under test: {@link ClassController#getClassById(String)}
     */
    @Test
    void testGetClassById() throws Exception {
        ClassEntity classEntity = new ClassEntity();
        classEntity.setAssistId("42");
        classEntity.setId("42");
        classEntity.setRespId("42");
        classEntity.setStuNum(10);
        classEntity.setTeacherId("42");
        when(this.classService.getClassById((String) any())).thenReturn(classEntity);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/get/class/{classId}", "42");
        MockMvcBuilders.standaloneSetup(this.classController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"id\":\"42\",\"respId\":\"42\",\"teacherId\":\"42\",\"assistId\":\"42\",\"stuNum\":10}"));
    }

    /**
     * Method under test: {@link ClassController#getClassById(String)}
     */
    @Test
    void testGetClassById2() throws Exception {
        ClassEntity classEntity = new ClassEntity();
        classEntity.setAssistId("42");
        classEntity.setId("42");
        classEntity.setRespId("42");
        classEntity.setStuNum(10);
        classEntity.setTeacherId("42");
        when(this.classService.getClassById((String) any())).thenReturn(classEntity);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/get/class/{classId}", "42");
        getResult.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(this.classController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"id\":\"42\",\"respId\":\"42\",\"teacherId\":\"42\",\"assistId\":\"42\",\"stuNum\":10}"));
    }

    /**
     * Method under test: {@link ClassController#insertClass(InsertClassDto)}
     */
    @Test
    void testInsertClass() throws Exception {
        when(this.classService.insertClass((InsertClassDto) any())).thenReturn("Insert Class");

        InsertClassDto insertClassDto = new InsertClassDto();
        insertClassDto.setAssistId("42");
        insertClassDto.setClassId("42");
        insertClassDto.setRespId("42");
        insertClassDto.setTeacherId("42");
        String content = (new ObjectMapper()).writeValueAsString(insertClassDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/post/class")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.classController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Insert Class"));
    }

    /**
     * Method under test: {@link ClassController#deleteClass(String)}
     */
    @Test
    void testDeleteClass() throws Exception {
        when(this.classService.deleteClass((String) any())).thenReturn("Delete Class");
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/delete/class/{classId}", "42");
        MockMvcBuilders.standaloneSetup(this.classController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Delete Class"));
    }

    /**
     * Method under test: {@link ClassController#deleteClass(String)}
     */
    @Test
    void testDeleteClass2() throws Exception {
        when(this.classService.deleteClass((String) any())).thenReturn("Delete Class");
        MockHttpServletRequestBuilder postResult = MockMvcRequestBuilders.post("/api/delete/class/{classId}", "42");
        postResult.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(this.classController)
                .build()
                .perform(postResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Delete Class"));
    }

    /**
     * Method under test: {@link ClassController#insertStudents(InsertStudentsDto)}
     */
    @Test
    void testInsertStudents() throws Exception {
        when(this.classService.insertStudents((InsertStudentsDto) any())).thenReturn("Insert Students");

        InsertStudentsDto insertStudentsDto = new InsertStudentsDto();
        insertStudentsDto.setClassId("42");
        insertStudentsDto.setFilePath("/directory/foo.txt");
        String content = (new ObjectMapper()).writeValueAsString(insertStudentsDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/post/students/class")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.classController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Insert Students"));
    }

    /**
     * Method under test: {@link ClassController#insertStudent(InsertStudentDto)}
     */
    @Test
    void testInsertStudent() throws Exception {
        when(this.classService.insertStudent((InsertStudentDto) any())).thenReturn("Insert Student");

        InsertStudentDto insertStudentDto = new InsertStudentDto();
        insertStudentDto.setClassId("42");
        insertStudentDto.setStuId("42");
        String content = (new ObjectMapper()).writeValueAsString(insertStudentDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/post/student")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.classController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Insert Student"));
    }

    /**
     * Method under test: {@link ClassController#deleteStudent(String, String)}
     */
    @Test
    void testDeleteStudent() throws Exception {
        when(this.classService.deleteStudent((String) any(), (String) any())).thenReturn("Delete Student");
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/delete/class/student")
                .param("classId", "foo")
                .param("studentId", "foo");
        MockMvcBuilders.standaloneSetup(this.classController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Delete Student"));
    }

    /**
     * Method under test: {@link ClassController#deleteStudent(String, String)}
     */
    @Test
    void testDeleteStudent2() throws Exception {
        when(this.classService.deleteStudent((String) any(), (String) any())).thenReturn("Delete Student");
        MockHttpServletRequestBuilder postResult = MockMvcRequestBuilders.post("/api/delete/class/student");
        postResult.contentType("https://example.org/example");
        MockHttpServletRequestBuilder requestBuilder = postResult.param("classId", "foo").param("studentId", "foo");
        MockMvcBuilders.standaloneSetup(this.classController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Delete Student"));
    }

    /**
     * Method under test: {@link ClassController#getAssist()}
     */
    @Test
    void testGetAssist() throws Exception {
        when(this.classService.getAllAssist()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/get/assist");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.classController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("?????"));
    }

    /**
     * Method under test: {@link ClassController#getAssist()}
     */
    @Test
    void testGetAssist2() throws Exception {
        TeacherEntity teacherEntity = new TeacherEntity();
        teacherEntity.setEmailAddr("42 Main St");
        teacherEntity.setGrade(true);
        teacherEntity.setId("42");
        teacherEntity.setName("?");
        teacherEntity.setPassword("iloveyou");
        teacherEntity.setReleaseLab(true);
        teacherEntity.setType(1);
        teacherEntity.setVerified(true);

        ArrayList<TeacherEntity> teacherEntityList = new ArrayList<>();
        teacherEntityList.add(teacherEntity);
        when(this.classService.getAllAssist()).thenReturn(teacherEntityList);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/get/assist");
        MockMvcBuilders.standaloneSetup(this.classController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "[{\"id\":\"42\",\"emailAddr\":\"42 Main St\",\"name\":\"?\",\"password\":\"iloveyou\",\"verified\":true,\"type\":1,\"grade"
                                        + "\":true,\"releaseLab\":true}]"));
    }

    /**
     * Method under test: {@link ClassController#getAssist()}
     */
    @Test
    void testGetAssist3() throws Exception {
        when(this.classService.getAllAssist()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/get/assist");
        getResult.contentType("https://example.org/example");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.classController)
                .build()
                .perform(getResult);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("?????"));
    }

    /**
     * Method under test: {@link ClassController#getClasses()}
     */
    @Test
    void testGetClasses() throws Exception {
        when(this.classService.getAllClasses()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/get/classes");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.classController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("?????"));
    }

    /**
     * Method under test: {@link ClassController#getClasses()}
     */
    @Test
    void testGetClasses2() throws Exception {
        ClassEntity classEntity = new ClassEntity();
        classEntity.setAssistId("42");
        classEntity.setId("42");
        classEntity.setRespId("42");
        classEntity.setStuNum(10);
        classEntity.setTeacherId("42");

        ArrayList<ClassEntity> classEntityList = new ArrayList<>();
        classEntityList.add(classEntity);
        when(this.classService.getAllClasses()).thenReturn(classEntityList);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/get/classes");
        MockMvcBuilders.standaloneSetup(this.classController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("[{\"id\":\"42\",\"respId\":\"42\",\"teacherId\":\"42\",\"assistId\":\"42\",\"stuNum\":10}]"));
    }

    /**
     * Method under test: {@link ClassController#getClasses()}
     */
    @Test
    void testGetClasses3() throws Exception {
        when(this.classService.getAllClasses()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/get/classes");
        getResult.contentType("https://example.org/example");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.classController)
                .build()
                .perform(getResult);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("?????"));
    }

    /**
     * Method under test: {@link ClassController#getResp()}
     */
    @Test
    void testGetResp() throws Exception {
        when(this.classService.getAllResp()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/get/resp");
        MockMvcBuilders.standaloneSetup(this.classController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link ClassController#getResp()}
     */
    @Test
    void testGetResp2() throws Exception {
        when(this.classService.getAllResp()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/get/resp");
        getResult.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(this.classController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link ClassController#getStudents(String)}
     */
    @Test
    void testGetStudents() throws Exception {
        when(this.classService.getAllStudentsByClassId((String) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/get/students/{classId}", "42");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.classController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("???????"));
    }

    /**
     * Method under test: {@link ClassController#getStudents(String)}
     */
    @Test
    void testGetStudents2() throws Exception {
        TakesEntity takesEntity = new TakesEntity();
        takesEntity.setClassId("42");
        takesEntity.setStuId("42");
        takesEntity.setStuName("?");

        ArrayList<TakesEntity> takesEntityList = new ArrayList<>();
        takesEntityList.add(takesEntity);
        when(this.classService.getAllStudentsByClassId((String) any())).thenReturn(takesEntityList);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/get/students/{classId}", "42");
        MockMvcBuilders.standaloneSetup(this.classController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[{\"stuId\":\"42\",\"classId\":\"42\",\"stuName\":\"?\"}]"));
    }

    /**
     * Method under test: {@link ClassController#getStudents(String)}
     */
    @Test
    void testGetStudents3() throws Exception {
        when(this.classService.getAllStudentsByClassId((String) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/get/students/{classId}", "42");
        getResult.contentType("https://example.org/example");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.classController)
                .build()
                .perform(getResult);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("???????"));
    }

    /**
     * Method under test: {@link ClassController#getTeacher()}
     */
    @Test
    void testGetTeacher() throws Exception {
        when(this.classService.getAllTeacher()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/get/teacher");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.classController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("???????"));
    }

    /**
     * Method under test: {@link ClassController#getTeacher()}
     */
    @Test
    void testGetTeacher2() throws Exception {
        TeacherEntity teacherEntity = new TeacherEntity();
        teacherEntity.setEmailAddr("42 Main St");
        teacherEntity.setGrade(true);
        teacherEntity.setId("42");
        teacherEntity.setName("?");
        teacherEntity.setPassword("iloveyou");
        teacherEntity.setReleaseLab(true);
        teacherEntity.setType(1);
        teacherEntity.setVerified(true);

        ArrayList<TeacherEntity> teacherEntityList = new ArrayList<>();
        teacherEntityList.add(teacherEntity);
        when(this.classService.getAllTeacher()).thenReturn(teacherEntityList);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/get/teacher");
        MockMvcBuilders.standaloneSetup(this.classController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "[{\"id\":\"42\",\"emailAddr\":\"42 Main St\",\"name\":\"?\",\"password\":\"iloveyou\",\"verified\":true,\"type\":1,\"grade"
                                        + "\":true,\"releaseLab\":true}]"));
    }

    /**
     * Method under test: {@link ClassController#getTeacher()}
     */
    @Test
    void testGetTeacher3() throws Exception {
        when(this.classService.getAllTeacher()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/get/teacher");
        getResult.contentType("https://example.org/example");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.classController)
                .build()
                .perform(getResult);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("???????"));
    }

    /**
     * Method under test: {@link ClassController#setAssistant(String, String)}
     */
    @Test
    void testSetAssistant() throws Exception {
        when(this.classService.setTA((String) any(), (String) any())).thenReturn("Ta");
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/post/set/assistant")
                .param("assistId", "foo")
                .param("classId", "foo");
        MockMvcBuilders.standaloneSetup(this.classController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Ta"));
    }

    /**
     * Method under test: {@link ClassController#setAssistant(String, String)}
     */
    @Test
    void testSetAssistant2() throws Exception {
        when(this.classService.setTA((String) any(), (String) any())).thenReturn("Ta");
        MockHttpServletRequestBuilder postResult = MockMvcRequestBuilders.post("/api/post/set/assistant");
        postResult.contentType("https://example.org/example");
        MockHttpServletRequestBuilder requestBuilder = postResult.param("assistId", "foo").param("classId", "foo");
        MockMvcBuilders.standaloneSetup(this.classController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Ta"));
    }

    /**
     * Method under test: {@link ClassController#setAttendanceRatio(Double)}
     */
    @Test
    void testSetAttendanceRatio() throws Exception {
        when(this.classService.setRatio((Double) any())).thenReturn("Ratio");
        MockHttpServletRequestBuilder postResult = MockMvcRequestBuilders.post("/api/post/attendance/ratio");
        MockHttpServletRequestBuilder requestBuilder = postResult.param("attendanceRatio", String.valueOf(10.0d));
        MockMvcBuilders.standaloneSetup(this.classController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Ratio"));
    }

    /**
     * Method under test: {@link ClassController#setAttendanceRatio(Double)}
     */
    @Test
    void testSetAttendanceRatio2() throws Exception {
        when(this.classService.setRatio((Double) any())).thenReturn("考勤比例超出合理范围");
        MockHttpServletRequestBuilder postResult = MockMvcRequestBuilders.post("/api/post/attendance/ratio");
        MockHttpServletRequestBuilder requestBuilder = postResult.param("attendanceRatio", String.valueOf(10.0d));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.classController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400))
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("??????????"));
    }

    /**
     * Method under test: {@link ClassController#setAttendanceRatio(Double)}
     */
    @Test
    void testSetAttendanceRatio3() throws Exception {
        when(this.classService.setRatio((Double) any())).thenReturn("Ratio");
        MockHttpServletRequestBuilder postResult = MockMvcRequestBuilders.post("/api/post/attendance/ratio");
        postResult.contentType("https://example.org/example");
        MockHttpServletRequestBuilder requestBuilder = postResult.param("attendanceRatio", String.valueOf(10.0d));
        MockMvcBuilders.standaloneSetup(this.classController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Ratio"));
    }
}

