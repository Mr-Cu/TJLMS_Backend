package edu.tongji.tjlms.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "query_report", schema = "lms", catalog = "")
@IdClass(QueryReportPK.class)
public class QueryReportEntity {
    private String stuId;
    private Integer labId;
    private String labName;
    private String aim;
    private String principle;
    private String step;
    private String updateDate;
    private String result;
    private Boolean mutable;
    private Boolean isChecked;

    @Id
    @Basic
    @Column(name = "stu_id")
    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    @Id
    @Basic
    @Column(name = "lab_id")
    public int getLabId() {
        return labId;
    }

    public void setLabId(int labId) {
        this.labId = labId;
    }

    @Basic
    @Column(name = "lab_name")
    public String getLabName() {
        return labName;
    }

    public void setLabName(String labName) {
        this.labName = labName;
    }

    @Basic
    @Column(name = "aim")
    public String getAim() {
        return aim;
    }

    public void setAim(String aim) {
        this.aim = aim;
    }

    @Basic
    @Column(name = "principle")
    public String getPrinciple() {
        return principle;
    }

    public void setPrinciple(String principle) {
        this.principle = principle;
    }

    @Basic
    @Column(name = "step")
    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step;
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
    @Column(name = "result")
    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Basic
    @Column(name = "mutable")
    public Boolean getMutable() {
        return mutable;
    }

    public void setMutable(Boolean mutable) {
        this.mutable = mutable;
    }

    @Basic
    @Column(name = "is_checked")
    public Boolean getChecked() {
        return isChecked;
    }

    public void setChecked(Boolean checked) {
        isChecked = checked;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QueryReportEntity that = (QueryReportEntity) o;
        return Objects.equals(labId, that.labId) && Objects.equals(stuId, that.stuId) && Objects.equals(labName, that.labName) && Objects.equals(aim, that.aim) && Objects.equals(principle, that.principle) && Objects.equals(step, that.step) && Objects.equals(updateDate, that.updateDate) && Objects.equals(result, that.result) && Objects.equals(mutable, that.mutable) && Objects.equals(isChecked, that.isChecked);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stuId, labId, labName, aim, principle, step, updateDate, result, mutable, isChecked);
    }
}
