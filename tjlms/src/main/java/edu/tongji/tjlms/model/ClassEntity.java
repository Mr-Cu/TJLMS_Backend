package edu.tongji.tjlms.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "class", schema = "lms", catalog = "")
public class ClassEntity {
    private String id;
    private String respId;
    private String teacherId;
    private String assistId;
    private Integer stuNum;

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "resp_id")
    public String getRespId() {
        return respId;
    }

    public void setRespId(String respId) {
        this.respId = respId;
    }

    @Basic
    @Column(name = "teacher_id")
    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    @Basic
    @Column(name = "assist_id")
    public String getAssistId() {
        return assistId;
    }

    public void setAssistId(String assistId) {
        this.assistId = assistId;
    }

    @Basic
    @Column(name = "stu_num")
    public Integer getStuNum() {
        return stuNum;
    }

    public void setStuNum(Integer stuNum) {
        this.stuNum = stuNum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClassEntity that = (ClassEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(respId, that.respId) && Objects.equals(teacherId, that.teacherId) && Objects.equals(assistId, that.assistId) && Objects.equals(stuNum, that.stuNum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, respId, teacherId, assistId, stuNum);
    }
}
