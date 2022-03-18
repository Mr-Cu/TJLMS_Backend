package edu.tongji.tjlms.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "report", schema = "lms", catalog = "")
@IdClass(ReportEntityPK.class)
public class ReportEntity {
    private String stuId;
    private Integer labId;
    private String classId;
    private String aim;
    private String principle;
    private String step;
    private String result;
    private String updateDate;
    private Boolean mutable;
    private Boolean isChecked;

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
    public Integer getLabId() {
        return labId;
    }


    public void setLabId(Integer labId) {
        this.labId = labId;
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
    @Column(name = "result")
    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
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
    @Column(name = "mutable")
    public Boolean getMutable() {
        return mutable;
    }

    public void setMutable(Boolean mutable) {
        this.mutable = mutable;
    }

    @Basic
    @Column(name = "is_checked")
    public Boolean getIsChecked() {
        return isChecked;
    }

    public void setIsChecked(Boolean isChecked) {
        this.isChecked = isChecked;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReportEntity that = (ReportEntity) o;
        return Objects.equals(stuId, that.stuId) && Objects.equals(labId, that.labId) && Objects.equals(classId, that.classId) && Objects.equals(aim, that.aim) && Objects.equals(principle, that.principle) && Objects.equals(step, that.step) && Objects.equals(result, that.result) && Objects.equals(updateDate, that.updateDate) && Objects.equals(mutable, that.mutable) && Objects.equals(isChecked,that.isChecked);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stuId, labId, classId, aim, principle, step, result, updateDate, mutable, isChecked);
    }
}




