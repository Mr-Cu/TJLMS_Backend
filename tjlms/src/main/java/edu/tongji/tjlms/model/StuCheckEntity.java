package edu.tongji.tjlms.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "stu_check", schema = "lms", catalog = "")
@IdClass(StuCheckEntityPK.class)
public class StuCheckEntity {
    private String stuId;
    private int checkId;
    private String time;

    @Id
    @Column(name = "stu_id", nullable = false, length = 45)
    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    @Id
    @Column(name = "check_id", nullable = false)
    public int getCheckId() {
        return checkId;
    }

    public void setCheckId(int checkId) {
        this.checkId = checkId;
    }

    @Basic
    @Column(name = "time", nullable = true, length = 200)
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StuCheckEntity that = (StuCheckEntity) o;
        return checkId == that.checkId && Objects.equals(stuId, that.stuId) && Objects.equals(time, that.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stuId, checkId, time);
    }
}
