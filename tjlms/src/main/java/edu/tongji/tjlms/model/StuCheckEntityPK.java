package edu.tongji.tjlms.model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class StuCheckEntityPK implements Serializable {
    private String stuId;
    private int checkId;

    @Column(name = "stu_id", nullable = false, length = 45)
    @Id
    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    @Column(name = "check_id", nullable = false)
    @Id
    public int getCheckId() {
        return checkId;
    }

    public void setCheckId(int checkId) {
        this.checkId = checkId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StuCheckEntityPK that = (StuCheckEntityPK) o;
        return checkId == that.checkId && Objects.equals(stuId, that.stuId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stuId, checkId);
    }
}
