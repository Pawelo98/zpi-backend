package com.crewmaker.dto;

import com.crewmaker.entity.EventPlaceOpinion;

public class EventPlaceOpinionDTO {
    private String userAuthor;
    private String title;
    private String message;
    private int grade;

    public EventPlaceOpinionDTO(EventPlaceOpinion opinion) {
        this.userAuthor = opinion.getUserAuthor().getUsername();
        this.title = opinion.getTitle();
        this.message = opinion.getMessage();
        this.grade = opinion.getGrade();
    }

    public String getUserAuthor() {
        return userAuthor;
    }

    public void setUserAuthor(String userAuthor) {
        this.userAuthor = userAuthor;
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
