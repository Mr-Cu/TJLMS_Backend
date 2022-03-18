package edu.tongji.tjlms.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "teacher", schema = "lms", catalog = "")
public class TeacherEntity {
    private String id;
    private String emailAddr;
    private String name;
    private String password;
    private Boolean verified;
    private Integer type;
    private Boolean grade;
    private Boolean releaseLab;

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "email_addr")
    public String getEmailAddr() {
        return emailAddr;
    }

    public void setEmailAddr(String emailAddr) {
        this.emailAddr = emailAddr;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "verified")
    public Boolean getVerified() {
        return verified;
    }

    public void setVerified(Boolean verified) {
        this.verified = verified;
    }

    @Basic
    @Column(name = "type")
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Basic
    @Column(name = "grade")
    public Boolean getGrade() {
        return grade;
    }

    public void setGrade(Boolean grade) {
        this.grade = grade;
    }

    @Basic
    @Column(name = "release_lab")
    public Boolean getReleaseLab() {
        return releaseLab;
    }

    public void setReleaseLab(Boolean releaseLab) {
        this.releaseLab = releaseLab;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeacherEntity teacher = (TeacherEntity) o;
        return id.equals(teacher.id) && emailAddr.equals(teacher.emailAddr) && name.equals(teacher.name) && password.equals(teacher.password) && verified.equals(teacher.verified) && type.equals(teacher.type) && grade.equals(teacher.grade) && releaseLab.equals(teacher.releaseLab);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, emailAddr, name, password, verified, type, grade, releaseLab);
    }
}
