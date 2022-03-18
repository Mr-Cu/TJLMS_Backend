package edu.tongji.tjlms.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@IdClass(QueryGradePK.class)
@Table(name = "all_grade", schema = "lms", catalog = "")
public class AllGradeEntity {
    private String stuId;
    private int labId;
    private String classId;
    private Double score;

    @Id
    @Column(name = "stu_id")
    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    @Id
    @Column(name = "lab_id")
    public int getLabId() {
        return labId;
    }

    public void setLabId(int labId) {
        this.labId = labId;
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
    @Column(name = "score")
    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AllGradeEntity that = (AllGradeEntity) o;
        return labId == that.labId && Objects.equals(stuId, that.stuId) && Objects.equals(classId, that.classId) && Objects.equals(score, that.score);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stuId, labId, classId, score);
    }
}
