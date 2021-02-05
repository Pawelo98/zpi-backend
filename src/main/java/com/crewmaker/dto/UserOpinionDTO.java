package com.crewmaker.dto;


import com.crewmaker.entity.UserOpinion;

public class UserOpinionDTO {
    private int userOpinionID;
    private String opinionAuthorName;
    private String title;
    private String message;
    private int grade;

    public UserOpinionDTO(UserOpinion opinion) {
        this.userOpinionID = opinion.getUserOpinionId();
        this.opinionAuthorName = opinion.getUserAuthor().getUsername();
        this.title = opinion.getTitle();
        this.message = opinion.getMessage();
        this.grade = opinion.getGrade();
    }

    public int getUserOpinionID() {
        return userOpinionID;
    }

    public void setUserOpinionID(int userOpinionID) {
        this.userOpinionID = userOpinionID;
    }

    public String getOpinionAuthorName() {
        return opinionAuthorName;
    }

    public void setOpinionAuthorName(String opinionAuthorName) {
        this.opinionAuthorName = opinionAuthorName;
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
}
