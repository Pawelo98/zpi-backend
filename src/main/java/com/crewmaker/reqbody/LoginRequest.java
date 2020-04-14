package com.crewmaker.reqbody;

import lombok.*;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class LoginRequest implements Serializable {
    private static final long serialVersionUID = 7710662072308044971L;

    @NotBlank
    private String username;

    @NotBlank
    private String password;

}