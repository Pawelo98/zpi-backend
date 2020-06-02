package com.crewmaker.reqbody;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Getter
@Setter
@ToString
@NoArgsConstructor
public class NewEventPlaceRequest {

    @NotBlank
    @Size(min = 3, max = 60)
    private String eventPlaceName;

    @NotBlank
    @Size(min = 3, max = 255)
    private String eventPlaceDescription;

    @NotBlank
    private int[] sportsCategory;

    @NotBlank
    @Size(min = 3, max = 60)
    private String eventPlaceCity;

    @NotBlank
    @Size(min = 0, max = 9)
    private String eventPlacePostalCode;

    @NotBlank
    @Size(min = 2, max =  60)
    private String eventPlaceStreet;

    @Size(min = 1, max = 20)
    private String eventPlaceStreetNumber;

    private String eventPlaceImage;

}
