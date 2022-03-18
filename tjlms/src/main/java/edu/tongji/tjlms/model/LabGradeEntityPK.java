package edu.tongji.tjlms.model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class LabGradeEntityPK implements Serializable {
    private String stuId;
    private Integer labId;

    @Column(name = "stu_id", nullable = false, length = 45)
    @Id
    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    @Column(name = "lab_id", nullable = false)
    @Id
    public Integer getLabId() {
        return labId;
    }

    public void setLabId(Integer labId) {
        this.labId = labId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LabGradeEntityPK that = (LabGradeEntityPK) o;
        return Objects.equals(stuId, that.stuId) && Objects.equals(labId, that.labId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stuId, labId);
    }
}
