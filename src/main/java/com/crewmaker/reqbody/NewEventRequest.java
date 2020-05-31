package com.crewmaker.reqbody;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import java.sql.Time;
import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class NewEventRequest {

    private int cycleId;

    @NotBlank
    private int eventPlaceId;

    @NotBlank
    private int sportCategoryId;

    @NotBlank
    private String eventName;

    @NotBlank
    private String eventDescription;

    @NotBlank
    private Date eventDate;

    @NotBlank
    private Time eventTime;

    @NotBlank
    private int maxPlayers;

    @NotBlank
    private boolean isCyclic;

    @NotBlank
    private Time eventDuration;
}