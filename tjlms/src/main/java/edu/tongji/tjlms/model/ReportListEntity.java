package edu.tongji.tjlms.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "report_list", schema = "lms", catalog = "")
@IdClass(ReportListPK.class)
public class ReportListEntity {
    private String stuId;
    private int labId;
    private String classId;
    private String stuName;
    private String teacherId;
    private String labName;
    private Boolean isChecked;
    private Boolean mutable;

    @Basic
    @Id
    @Column(name = "stu_id")
    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    @Basic
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
    @Column(name = "stu_name")
    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
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
    @Column(name = "lab_name")
    public String getLabName() {
        return labName;
    }

    public void setLabName(String labName) {
        this.labName = labName;
    }

    @Basic
    @Column(name = "is_checked")
    public Boolean getChecked() {
        return isChecked;
    }

    public void setChecked(Boolean checked) {
        isChecked = checked;
    }

    @Basic
    @Column(name = "mutable")
    public Boolean getMutable() {
        return mutable;
    }

    public void setMutable(Boolean mutable) {
        this.mutable = mutable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReportListEntity that = (ReportListEntity) o;
        return labId == that.labId && Objects.equals(stuId, that.stuId) && Objects.equals(classId, that.classId) && Objects.equals(stuName, that.stuName) && Objects.equals(teacherId, that.teacherId) && Objects.equals(labName, that.labName) && Objects.equals(isChecked, that.isChecked) && Objects.equals(mutable, that.mutable);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stuId, labId, classId, stuName, teacherId, labName, isChecked, mutable);
    }
}
