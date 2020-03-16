package com.crewmaker.entity;

import javax.persistence.*;

@Entity
@Table(name="useropinion")
public class UserOpinion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="userOpinionID")
    private int userOpinionId;

    @ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="userAuthorID")
    private User userAuthor;

    @ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="userAboutID")
    private User userAbout;

    @Column(name="title")
    private String title;

    @Column(name="message")
    private String message;

    @Column(name="grade")
    private int grade;

    public UserOpinion(User userAuthor, User userAbout, String title, String message, int grade) {
        this.userAuthor = userAuthor;
        this.userAbout = userAbout;
        this.title = title;
        this.message = message;
        this.grade = grade;
    }

    public UserOpinion() {}

    public int getUserOpinionId() {
        return userOpinionId;
    }

    public void setUserOpinionId(int userOpinionId) {
        this.userOpinionId = userOpinionId;
    }

    public User getUserAuthor() {
        return userAuthor;
    }

    public void setUserAuthor(User userAuthor) {
        this.userAuthor = userAuthor;
    }

    public User getUserAbout() {
        return userAbout;
    }

    public void setUserAbout(User userAbout) {
        this.userAbout = userAbout;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "UserOpinion{" +
                "userOpinionId=" + userOpinionId +
                ", title='" + title + '\'' +
                ", message='" + message + '\'' +
                ", grade=" + grade +
                '}';
    }
}
