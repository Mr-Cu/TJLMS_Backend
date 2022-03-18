package edu.tongji.tjlms.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "takes", schema = "lms", catalog = "")
@IdClass(TakesEntityPK.class)
public class TakesEntity {
    private String stuId;
    private String classId;
    private String stuName;

    @Id
    @Column(name = "stu_id")
    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    @Id
    @Column(name = "class_id")
    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    @Basic
    @Column(name = "stu_name")
    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TakesEntity that = (TakesEntity) o;
        return Objects.equals(stuId, that.stuId) && Objects.equals(classId, that.classId) && Objects.equals(stuName, that.stuName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stuId, classId, stuName);
    }
}
