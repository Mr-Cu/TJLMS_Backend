package edu.tongji.tjlms.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "lab", schema = "lms", catalog = "")
public class LabEntity {
    private Integer id;
    private String name;
    private String intro;
    private String releaseTeacher;
    private String releaseDate;
    private String deadline;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
    @Column(name="intro")
    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    @Basic
    @Column(name = "release_teacher")
    public String getReleaseTeacher() {
        return releaseTeacher;
    }

    public void setReleaseTeacher(String releaseTeacher) {
        this.releaseTeacher = releaseTeacher;
    }

    @Basic
    @Column(name = "release_date")
    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Basic
    @Column(name = "deadline")
    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LabEntity labEntity = (LabEntity) o;
        return Objects.equals(id, labEntity.id) && Objects.equals(name, labEntity.name) && Objects.equals(releaseTeacher, labEntity.releaseTeacher) && Objects.equals(releaseDate, labEntity.releaseDate) && Objects.equals(deadline, labEntity.deadline);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, releaseTeacher, releaseDate, deadline);
    }
}
