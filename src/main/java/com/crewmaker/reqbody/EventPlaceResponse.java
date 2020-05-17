package com.crewmaker.reqbody;

import com.crewmaker.entity.EventPlace;
import com.crewmaker.entity.EventPlaceSportsCategory;
import com.crewmaker.entity.SportsCategory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
public class EventPlaceResponse {
    private int eventPlaceID;
    private String eventPlaceName;
    private String eventPlaceDescription;
    private String eventPlaceStreetName;
    private String eventPlaceStreetNumber;
    private String eventPlacePostCode;
    private String eventPlaceCity;
    //private Set<EventPlaceSportsCategory> eventPlaceSportsCategories;
    private Set<SportsCategory> sportsCategories;
    private String userRequestingUsername;
    private String userAcceptingUsername;
    private Boolean isAccepted;
    private Boolean isArchived;

    public EventPlaceResponse(EventPlace eventPlace){
        eventPlaceID = eventPlace.getEventPlaceId();
        eventPlaceName = eventPlace.getName();
        eventPlaceDescription = eventPlace.getDescription();
        eventPlaceStreetName = eventPlace.getStreet();
        eventPlaceStreetNumber = eventPlace.getStreetNumber();
        eventPlacePostCode = eventPlace.getPostCode();
        eventPlaceCity = eventPlace.getCity();
        sportsCategories = eventPlace.getSportsCategory();
        //eventPlaceSportsCategories = eventPlace.getEventPlaceSportsCategories().;
        userRequestingUsername = eventPlace.getUserRequestingUsername();
        isAccepted = eventPlace.getAccepted();

        if(isAccepted == false)
            userAcceptingUsername = "";
        else
            userAcceptingUsername = eventPlace.getUserAcceptingUsername();

        isArchived = eventPlace.getArchived();
        //sportsCategories =eventPlace.getEventPlaceSportsCategories();
    }
}