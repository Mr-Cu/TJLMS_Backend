package edu.tongji.tjlms.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "sign_in", schema = "lms", catalog = "")
public class CheckEntity {
    private int id;
    private String classId;
    private String startTime;
    private String endTime;
    private Integer checkedStudent;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "class_id")
    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    @Basic
    @Column(name = "start_time")
    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    @Basic
    @Column(name = "end_time")
    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }


    @Basic
    @Column(name = "checked_student")
    public Integer getCheckedStudent() {
        return checkedStudent;
    }

    public void setCheckedStudent(Integer checkedStudent) {
        this.checkedStudent = checkedStudent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CheckEntity that = (CheckEntity) o;
        return id == that.id && Objects.equals(classId, that.classId) && Objects.equals(startTime, that.startTime) && Objects.equals(endTime, that.endTime) && Objects.equals(checkedStudent,that.checkedStudent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, classId, startTime, endTime,checkedStudent);
    }
}
