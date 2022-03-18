package edu.tongji.tjlms.model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class QueryReportPK implements Serializable {
    private String stuId;
    private Integer labId;

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
        QueryReportPK that = (QueryReportPK) o;
        return stuId.equals(that.stuId) && labId.equals(that.labId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stuId, labId);
    }
}
