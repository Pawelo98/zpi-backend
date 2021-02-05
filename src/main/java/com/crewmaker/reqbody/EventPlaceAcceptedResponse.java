package com.crewmaker.reqbody;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class EventPlaceAcceptedResponse {
    private Boolean success;
    private Boolean isAccepted;
    private String userAcceptingUsername;
    private String message;
}

