package edu.tongji.tjlms.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "feedback", schema = "lms")
public class FeedbackEntity {
    private Integer id;
    private String feedbacker;
    private String fbTitle;
    private String fbContent;
    private String fbTime;
    private String replier;
    private String rpTitle;
    private String rpContent;
    private String rpTime;
    private Boolean isReplied;
    private Boolean isAnonymous;

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
    @Column(name = "feedbacker")
    public String getFeedbacker() {
        return feedbacker;
    }

    public void setFeedbacker(String feedbacker) {
        this.feedbacker = feedbacker;
    }

    @Basic
    @Column(name = "fb_title")
    public String getFbTitle() {
        return fbTitle;
    }

    public void setFbTitle(String fbTitle) {
        this.fbTitle = fbTitle;
    }

    @Basic
    @Column(name = "fb_content")
    public String getFbContent() {
        return fbContent;
    }

    public void setFbContent(String fbContent) {
        this.fbContent = fbContent;
    }

    @Basic
    @Column(name = "fb_time")
    public String getFbTime() {
        return fbTime;
    }

    public void setFbTime(String fbTime) {
        this.fbTime = fbTime;
    }

    @Basic
    @Column(name = "replier")
    public String getReplier() {
        return replier;
    }

    public void setReplier(String replier) {
        this.replier = replier;
    }

    @Basic
    @Column(name = "rp_title")
    public String getRpTitle() {
        return rpTitle;
    }

    public void setRpTitle(String rpTitle) {
        this.rpTitle = rpTitle;
    }

    @Basic
    @Column(name = "rp_content")
    public String getRpContent() {
        return rpContent;
    }

    public void setRpContent(String rpContent) {
        this.rpContent = rpContent;
    }

    @Basic
    @Column(name = "rp_time")
    public String getRpTime() {
        return rpTime;
    }

    public void setRpTime(String rpTime) {
        this.rpTime = rpTime;
    }

    @Basic
    @Column(name = "is_replied")
    public Boolean getIsReplied() {
        return isReplied;
    }

    public void setIsReplied(Boolean isReplied) {
        this.isReplied = isReplied;
    }

    @Basic
    @Column(name = "is_anonymous")
    public Boolean getAnonymous() {
        return isAnonymous;
    }

    public void setAnonymous(Boolean anonymous) {
        isAnonymous = anonymous;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FeedbackEntity feedback = (FeedbackEntity) o;
        return Objects.equals(isAnonymous,feedback.isAnonymous)&&Objects.equals(id, feedback.id) && Objects.equals(feedbacker, feedback.feedbacker) && Objects.equals(fbTitle, feedback.fbTitle) && Objects.equals(fbContent, feedback.fbContent) && Objects.equals(fbTime, feedback.fbTime) && Objects.equals(rpTitle, feedback.rpTitle) && Objects.equals(rpContent, feedback.rpContent) && Objects.equals(rpTime, feedback.rpTime) && Objects.equals(isReplied, feedback.isReplied);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, feedbacker, fbTitle, fbContent, fbTime, rpTitle, rpContent, rpTime, isReplied,isAnonymous);
    }
}
