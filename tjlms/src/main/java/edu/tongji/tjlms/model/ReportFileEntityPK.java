package edu.tongji.tjlms.model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class ReportFileEntityPK implements Serializable {
    private String stuId;
    private int labId;

    @Column(name = "stu_id")
    @Id
    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    @Column(name = "lab_id")
    @Id
    public int getLabId() {
        return labId;
    }

    public void setLabId(int labId) {
        this.labId = labId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReportFileEntityPK that = (ReportFileEntityPK) o;
        return labId == that.labId && Objects.equals(stuId, that.stuId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stuId, labId);
    }
}
