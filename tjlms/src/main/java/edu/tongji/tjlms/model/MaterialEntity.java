package edu.tongji.tjlms.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "material", schema = "lms", catalog = "")
public class MaterialEntity {
    private Integer id;
    private String location;
    private Integer labId;
    private String uploader;
    private String name;
    private String uploadTime;

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
    @Column(name = "location")
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Basic
    @Column(name = "lab_id")
    public Integer getLabId() {
        return labId;
    }

    public void setLabId(Integer labId) {
        this.labId = labId;
    }

    @Basic
    @Column(name = "uploader")
    public String getUploader() {
        return uploader;
    }

    public void setUploader(String uploader) {
        this.uploader = uploader;
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
    @Column(name = "upload_time")
    public String getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(String uploadTime) {
        this.uploadTime = uploadTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MaterialEntity that = (MaterialEntity) o;
        return Objects.equals(location, that.location) && Objects.equals(labId, that.labId) && Objects.equals(uploader, that.uploader) && Objects.equals(name,that.name) && Objects.equals(uploadTime,that.uploadTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(location, labId, uploader,name,uploadTime);
    }

    public MaterialEntity() {
    }

    public MaterialEntity(String location, Integer labId, String uploader, String name, String uploadTime) {
        this.location = location;
        this.labId = labId;
        this.uploader = uploader;
        this.name = name;
        this.uploadTime = uploadTime;
    }
}
