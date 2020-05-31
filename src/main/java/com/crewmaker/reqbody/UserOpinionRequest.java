package com.crewmaker.reqbody;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
@NoArgsConstructor

public class UserOpinionRequest {

        private String userAuthor;

        private String userAbout;

        @NotBlank
        private String title;

        @NotBlank
        private String message;

        private int grade;
}
