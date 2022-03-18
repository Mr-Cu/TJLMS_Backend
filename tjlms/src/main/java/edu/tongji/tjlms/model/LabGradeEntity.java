package edu.tongji.tjlms.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "lab_grade", schema = "lms", catalog = "")
@IdClass(LabGradeEntityPK.class)
public class LabGradeEntity {
    private String stuId;
    private String classId;
    private String updateDate;
    private Boolean visible;
    private Integer labId;
    private Double score;
    private String note;
    private String teacherId;

    @Id
    @Column(name = "stu_id")
    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    @Id
    @Column(name = "lab_id", nullable = false)
    public Integer getLabId() {
        return labId;
    }
    public void setLabId(Integer labId) {
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
    @Column(name = "update_date")
    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    @Basic
    @Column(name = "visible")
    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    @Basic
    @Column(name = "score")
    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    @Basic
    @Column(name = "note")
    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Basic
    @Column(name = "teacher_id")
    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LabGradeEntity that = (LabGradeEntity) o;
        return Objects.equals(stuId, that.stuId) && Objects.equals(classId, that.classId) && Objects.equals(updateDate, that.updateDate) && Objects.equals(visible, that.visible) && Objects.equals(labId, that.labId) && Objects.equals(score, that.score) && Objects.equals(note, that.note) && Objects.equals(teacherId, that.teacherId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stuId, classId, updateDate, visible, labId, score, note, teacherId);
    }
}
