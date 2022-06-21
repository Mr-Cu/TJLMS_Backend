package edu.tongji.tjlms.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.tongji.tjlms.dto.PermDto;
import edu.tongji.tjlms.model.StudentEntity;
import edu.tongji.tjlms.model.TeacherEntity;
import edu.tongji.tjlms.service.user.UserService;
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

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {UserManageController.class})
@ExtendWith(SpringExtension.class)
class UserManageControllerTest {
    @Autowired
    private UserManageController userManageController;

    @MockBean
    private UserService userService;

    /**
     * Method under test: {@link UserManageController#deleteStudentById(String)}
     */
    @Test
    void testDeleteStudentById() throws Exception {
        when(this.userService.deleteStudent((String) any())).thenReturn("Delete Student");
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/delete/student/{studentId}", "42");
        MockMvcBuilders.standaloneSetup(this.userManageController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Delete Student"));
    }

    /**
     * Method under test: {@link UserManageController#getAllStudents()}
     */
    @Test
    void testGetAllStudents() throws Exception {
        when(this.userService.getAllStudents()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/get/all/students");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.userManageController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("??????"));
    }

    /**
     * Method under test: {@link UserManageController#getAllStudents()}
     */
    @Test
    void testGetAllStudents2() throws Exception {
        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setEmailAddr("42 Main St");
        studentEntity.setId("42");
        studentEntity.setName("?");
        studentEntity.setPassword("iloveyou");
        studentEntity.setVerified(true);

        ArrayList<StudentEntity> studentEntityList = new ArrayList<>();
        studentEntityList.add(studentEntity);
        when(this.userService.getAllStudents()).thenReturn(studentEntityList);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/get/all/students");
        MockMvcBuilders.standaloneSetup(this.userManageController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "[{\"id\":\"42\",\"emailAddr\":\"42 Main St\",\"name\":\"?\",\"password\":\"iloveyou\",\"verified\":true}]"));
    }

    /**
     * Method under test: {@link UserManageController#getAllStudents()}
     */
    @Test
    void testGetAllStudents3() throws Exception {
        when(this.userService.getAllStudents()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/get/all/students");
        getResult.contentType("https://example.org/example");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.userManageController)
                .build()
                .perform(getResult);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("??????"));
    }

    /**
     * Method under test: {@link UserManageController#getAllTeachers()}
     */
    @Test
    void testGetAllTeachers() throws Exception {
        when(this.userService.getAllTeachers()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/get/all/teachers");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.userManageController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("??????"));
    }

    /**
     * Method under test: {@link UserManageController#getAllTeachers()}
     */
    @Test
    void testGetAllTeachers2() throws Exception {
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
        when(this.userService.getAllTeachers()).thenReturn(teacherEntityList);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/get/all/teachers");
        MockMvcBuilders.standaloneSetup(this.userManageController)
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
     * Method under test: {@link UserManageController#getAllTeachers()}
     */
    @Test
    void testGetAllTeachers3() throws Exception {
        when(this.userService.getAllTeachers()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/get/all/teachers");
        getResult.contentType("https://example.org/example");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.userManageController)
                .build()
                .perform(getResult);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("??????"));
    }

    /**
     * Method under test: {@link UserManageController#insertStudent(StudentEntity)}
     */
    @Test
    void testInsertStudent() throws Exception {
        when(this.userService.insertStudent((StudentEntity) any())).thenReturn("Insert Student");

        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setEmailAddr("42 Main St");
        studentEntity.setId("42");
        studentEntity.setName("Name");
        studentEntity.setPassword("iloveyou");
        studentEntity.setVerified(true);
        String content = (new ObjectMapper()).writeValueAsString(studentEntity);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/admin/post/student")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.userManageController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Insert Student"));
    }

    /**
     * Method under test: {@link UserManageController#insertStudents(String)}
     */
    @Test
    void testInsertStudents() throws Exception {
        when(this.userService.insertStudent((String) any())).thenReturn("Insert Student");
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/post/students")
                .param("filePath", "foo");
        MockMvcBuilders.standaloneSetup(this.userManageController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Insert Student"));
    }

    /**
     * Method under test: {@link UserManageController#insertStudents(String)}
     */
    @Test
    void testInsertStudents2() throws Exception {
        when(this.userService.insertStudent((String) any())).thenReturn("添加失败");
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/post/students")
                .param("filePath", "foo");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.userManageController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400))
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("????"));
    }

    /**
     * Method under test: {@link UserManageController#insertStudents(String)}
     */
    @Test
    void testInsertStudents3() throws Exception {
        when(this.userService.insertStudent((String) any())).thenReturn("Insert Student");
        MockHttpServletRequestBuilder postResult = MockMvcRequestBuilders.post("/api/post/students");
        postResult.contentType("https://example.org/example");
        MockHttpServletRequestBuilder requestBuilder = postResult.param("filePath", "foo");
        MockMvcBuilders.standaloneSetup(this.userManageController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Insert Student"));
    }

    /**
     * Method under test: {@link UserManageController#insertTeacher(TeacherEntity)}
     */
    @Test
    void testInsertTeacher() throws Exception {
        when(this.userService.insertTeacher((TeacherEntity) any())).thenReturn("Insert Teacher");

        TeacherEntity teacherEntity = new TeacherEntity();
        teacherEntity.setEmailAddr("42 Main St");
        teacherEntity.setGrade(true);
        teacherEntity.setId("42");
        teacherEntity.setName("Name");
        teacherEntity.setPassword("iloveyou");
        teacherEntity.setReleaseLab(true);
        teacherEntity.setType(1);
        teacherEntity.setVerified(true);
        String content = (new ObjectMapper()).writeValueAsString(teacherEntity);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/admin/post/teacher")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.userManageController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Insert Teacher"));
    }

    /**
     * Method under test: {@link UserManageController#insertTeachers(String)}
     */
    @Test
    void testInsertTeachers() throws Exception {
        when(this.userService.insertTeacher((String) any())).thenReturn("Insert Teacher");
        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders.post("/api/post/teachers")
                .contentType(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = new ObjectMapper();
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult
                .content(objectMapper.writeValueAsString(new String()));
        MockMvcBuilders.standaloneSetup(this.userManageController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Insert Teacher"));
    }

    /**
     * Method under test: {@link UserManageController#insertTeachers(String)}
     */
    @Test
    void testInsertTeachers2() throws Exception {
        when(this.userService.insertTeacher((String) any())).thenReturn("添加失败");
        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders.post("/api/post/teachers")
                .contentType(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = new ObjectMapper();
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult
                .content(objectMapper.writeValueAsString(new String()));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.userManageController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400))
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("????"));
    }

    /**
     * Method under test: {@link UserManageController#modifyPerm(PermDto)}
     */
    @Test
    void testModifyPerm() throws Exception {
        when(this.userService.modifyPerm((PermDto) any())).thenReturn("Modify Perm");

        PermDto permDto = new PermDto();
        permDto.setGrade(true);
        permDto.setId("42");
        permDto.setReleaseLab(true);
        permDto.setType(1);
        String content = (new ObjectMapper()).writeValueAsString(permDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/post/modify")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.userManageController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Modify Perm"));
    }

    /**
     * Method under test: {@link UserManageController#modifyPerm(java.util.List)}
     */
    @Test
    void testModifyPerm2() throws Exception {
        when(this.userService.modifyPerm((java.util.List<PermDto>) any())).thenReturn("Modify Perm");
        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders.post("/api/post/modify/batch")
                .contentType(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = new ObjectMapper();
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult
                .content(objectMapper.writeValueAsString(new ArrayList<>()));
        MockMvcBuilders.standaloneSetup(this.userManageController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Modify Perm"));
    }

    /**
     * Method under test: {@link UserManageController#getStudentsPaged(Integer, Integer)}
     */
    @Test
    void testGetStudentsPaged() throws Exception {
        when(this.userService.getStudentsPaged((Integer) any(), (Integer) any()))
                .thenReturn(new PageImpl<>(new ArrayList<>()));
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/get/students/page");
        MockHttpServletRequestBuilder paramResult = getResult.param("pageNum", String.valueOf(1));
        MockHttpServletRequestBuilder requestBuilder = paramResult.param("pageSize", String.valueOf(1));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.userManageController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("??????"));
    }

    /**
     * Method under test: {@link UserManageController#getStudentsPaged(Integer, Integer)}
     */
    @Test
    void testGetStudentsPaged2() throws Exception {
        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setEmailAddr("42 Main St");
        studentEntity.setId("42");
        studentEntity.setName("?");
        studentEntity.setPassword("iloveyou");
        studentEntity.setVerified(true);

        ArrayList<StudentEntity> studentEntityList = new ArrayList<>();
        studentEntityList.add(studentEntity);
        PageImpl<StudentEntity> pageImpl = new PageImpl<>(studentEntityList);
        when(this.userService.getStudentsPaged((Integer) any(), (Integer) any())).thenReturn(pageImpl);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/get/students/page");
        MockHttpServletRequestBuilder paramResult = getResult.param("pageNum", String.valueOf(1));
        MockHttpServletRequestBuilder requestBuilder = paramResult.param("pageSize", String.valueOf(1));
        MockMvcBuilders.standaloneSetup(this.userManageController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"data\":{\"content\":[{\"id\":\"42\",\"emailAddr\":\"42 Main St\",\"name\":\"?\",\"password\":\"iloveyou\",\"verified\""
                                        + ":true}],\"pageable\":\"INSTANCE\",\"totalPages\":1,\"totalElements\":1,\"last\":true,\"number\":0,\"size\":1,\"sort"
                                        + "\":{\"sorted\":false,\"unsorted\":true,\"empty\":true},\"numberOfElements\":1,\"first\":true,\"empty\":false},"
                                        + "\"pageNum\":1}"));
    }

    /**
     * Method under test: {@link UserManageController#getStudentsPaged(Integer, Integer)}
     */
    @Test
    void testGetStudentsPaged3() throws Exception {
        when(this.userService.getStudentsPaged((Integer) any(), (Integer) any())).thenReturn(null);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/get/students/page");
        MockHttpServletRequestBuilder paramResult = getResult.param("pageNum", String.valueOf(1));
        MockHttpServletRequestBuilder requestBuilder = paramResult.param("pageSize", String.valueOf(1));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.userManageController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400))
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("???????"));
    }

    /**
     * Method under test: {@link UserManageController#getStudentsPaged(Integer, Integer)}
     */
    @Test
    void testGetStudentsPaged4() throws Exception {
        when(this.userService.getStudentsPaged((Integer) any(), (Integer) any()))
                .thenReturn(new PageImpl<>(new ArrayList<>()));
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/get/students/page");
        MockHttpServletRequestBuilder paramResult = getResult.param("pageNum", String.valueOf(0));
        MockHttpServletRequestBuilder requestBuilder = paramResult.param("pageSize", String.valueOf(1));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.userManageController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("??????"));
    }

    /**
     * Method under test: {@link UserManageController#getStudentsPaged(Integer, Integer)}
     */
    @Test
    void testGetStudentsPaged5() throws Exception {
        when(this.userService.getStudentsPaged((Integer) any(), (Integer) any()))
                .thenReturn(new PageImpl<>(new ArrayList<>()));
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/get/students/page");
        MockHttpServletRequestBuilder paramResult = getResult.param("pageNum", String.valueOf(-1));
        MockHttpServletRequestBuilder requestBuilder = paramResult.param("pageSize", String.valueOf(1));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.userManageController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("??????"));
    }

    /**
     * Method under test: {@link UserManageController#getStudentsPaged(Integer, Integer)}
     */
    @Test
    void testGetStudentsPaged6() throws Exception {
        when(this.userService.getStudentsPaged((Integer) any(), (Integer) any()))
                .thenReturn(new PageImpl<>(new ArrayList<>()));
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/get/students/page");
        getResult.contentType("https://example.org/example");
        MockHttpServletRequestBuilder paramResult = getResult.param("pageNum", String.valueOf(1));
        MockHttpServletRequestBuilder requestBuilder = paramResult.param("pageSize", String.valueOf(1));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.userManageController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("??????"));
    }

    /**
     * Method under test: {@link UserManageController#getTeachersPaged(Integer, Integer)}
     */
    @Test
    void testGetTeachersPaged() throws Exception {
        when(this.userService.getTeachersPaged((Integer) any(), (Integer) any()))
                .thenReturn(new PageImpl<>(new ArrayList<>()));
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/get/teachers/page");
        MockHttpServletRequestBuilder paramResult = getResult.param("pageNum", String.valueOf(1));
        MockHttpServletRequestBuilder requestBuilder = paramResult.param("pageSize", String.valueOf(1));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.userManageController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("??????"));
    }

    /**
     * Method under test: {@link UserManageController#getTeachersPaged(Integer, Integer)}
     */
    @Test
    void testGetTeachersPaged2() throws Exception {
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
        PageImpl<TeacherEntity> pageImpl = new PageImpl<>(teacherEntityList);
        when(this.userService.getTeachersPaged((Integer) any(), (Integer) any())).thenReturn(pageImpl);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/get/teachers/page");
        MockHttpServletRequestBuilder paramResult = getResult.param("pageNum", String.valueOf(1));
        MockHttpServletRequestBuilder requestBuilder = paramResult.param("pageSize", String.valueOf(1));
        MockMvcBuilders.standaloneSetup(this.userManageController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"data\":{\"content\":[{\"id\":\"42\",\"emailAddr\":\"42 Main St\",\"name\":\"?\",\"password\":\"iloveyou\",\"verified\""
                                        + ":true,\"type\":1,\"grade\":true,\"releaseLab\":true}],\"pageable\":\"INSTANCE\",\"totalPages\":1,\"totalElements\""
                                        + ":1,\"last\":true,\"number\":0,\"size\":1,\"sort\":{\"sorted\":false,\"unsorted\":true,\"empty\":true},\"numberOfElements"
                                        + "\":1,\"first\":true,\"empty\":false},\"pageNum\":1}"));
    }

    /**
     * Method under test: {@link UserManageController#getTeachersPaged(Integer, Integer)}
     */
    @Test
    void testGetTeachersPaged3() throws Exception {
        when(this.userService.getTeachersPaged((Integer) any(), (Integer) any())).thenReturn(null);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/get/teachers/page");
        MockHttpServletRequestBuilder paramResult = getResult.param("pageNum", String.valueOf(1));
        MockHttpServletRequestBuilder requestBuilder = paramResult.param("pageSize", String.valueOf(1));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.userManageController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400))
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("???????"));
    }

    /**
     * Method under test: {@link UserManageController#getTeachersPaged(Integer, Integer)}
     */
    @Test
    void testGetTeachersPaged4() throws Exception {
        when(this.userService.getTeachersPaged((Integer) any(), (Integer) any()))
                .thenReturn(new PageImpl<>(new ArrayList<>()));
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/get/teachers/page");
        MockHttpServletRequestBuilder paramResult = getResult.param("pageNum", String.valueOf(0));
        MockHttpServletRequestBuilder requestBuilder = paramResult.param("pageSize", String.valueOf(1));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.userManageController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("??????"));
    }

    /**
     * Method under test: {@link UserManageController#getTeachersPaged(Integer, Integer)}
     */
    @Test
    void testGetTeachersPaged5() throws Exception {
        when(this.userService.getTeachersPaged((Integer) any(), (Integer) any()))
                .thenReturn(new PageImpl<>(new ArrayList<>()));
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/get/teachers/page");
        MockHttpServletRequestBuilder paramResult = getResult.param("pageNum", String.valueOf(-1));
        MockHttpServletRequestBuilder requestBuilder = paramResult.param("pageSize", String.valueOf(1));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.userManageController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("??????"));
    }

    /**
     * Method under test: {@link UserManageController#getTeachersPaged(Integer, Integer)}
     */
    @Test
    void testGetTeachersPaged6() throws Exception {
        when(this.userService.getTeachersPaged((Integer) any(), (Integer) any()))
                .thenReturn(new PageImpl<>(new ArrayList<>()));
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/get/teachers/page");
        getResult.contentType("https://example.org/example");
        MockHttpServletRequestBuilder paramResult = getResult.param("pageNum", String.valueOf(1));
        MockHttpServletRequestBuilder requestBuilder = paramResult.param("pageSize", String.valueOf(1));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.userManageController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("??????"));
    }

    /**
     * Method under test: {@link UserManageController#deleteStudentById(String)}
     */
    @Test
    void testDeleteStudentById2() throws Exception {
        when(this.userService.deleteStudent((String) any())).thenReturn("Delete Student");
        MockHttpServletRequestBuilder postResult = MockMvcRequestBuilders.post("/api/delete/student/{studentId}", "42");
        postResult.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(this.userManageController)
                .build()
                .perform(postResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Delete Student"));
    }

    /**
     * Method under test: {@link UserManageController#deleteTeacherById(String)}
     */
    @Test
    void testDeleteTeacherById() throws Exception {
        when(this.userService.deleteTeacher((String) any())).thenReturn("Delete Teacher");
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/delete/teacher/{studentId}", "42");
        MockMvcBuilders.standaloneSetup(this.userManageController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Delete Teacher"));
    }

    /**
     * Method under test: {@link UserManageController#deleteTeacherById(String)}
     */
    @Test
    void testDeleteTeacherById2() throws Exception {
        when(this.userService.deleteTeacher((String) any())).thenReturn("Delete Teacher");
        MockHttpServletRequestBuilder postResult = MockMvcRequestBuilders.post("/api/delete/teacher/{studentId}", "42");
        postResult.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(this.userManageController)
                .build()
                .perform(postResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Delete Teacher"));
    }
}

