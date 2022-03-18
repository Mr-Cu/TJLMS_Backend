package edu.tongji.tjlms.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "admin", schema = "lms", catalog = "")
public class AdminEntity {
    private String id;
    private String emailAddr;
    private String name;
    private String password;
    private String telNum;
    private Boolean pwdReset;

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
    @Column(name = "tel_num")
    public String getTelNum() {
        return telNum;
    }

    public void setTelNum(String telNum) {
        this.telNum = telNum;
    }

    @Basic
    @Column(name = "pwd_reset")
    public Boolean getPwdReset()
    {
        return pwdReset;
    }
    public void setPwdReset(Boolean pwdReset)
    {
        this.pwdReset = pwdReset;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdminEntity that = (AdminEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(emailAddr, that.emailAddr) && Objects.equals(name, that.name) && Objects.equals(password, that.password) && Objects.equals(telNum, that.telNum) && Objects.equals(pwdReset,that.pwdReset);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, emailAddr, name, password, telNum, pwdReset);
    }
}
