package com.crewmaker.reqbody;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class EventPlaceStatisticsResponse {
    int allEventsCount;
    int passedEventsCount;
    int incomingEventsCount;
}
