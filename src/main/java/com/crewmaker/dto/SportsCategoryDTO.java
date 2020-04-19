package com.crewmaker.dto;

import com.crewmaker.entity.SportsCategory;

public class SportsCategoryDTO {
    private int sportsCategoryID;
    private String sportsCategoryName;

    public SportsCategoryDTO(SportsCategory sportsCategory) {
        this.sportsCategoryID = sportsCategory.getSportsCategoryId();
        this.sportsCategoryName = sportsCategory.getSportCategoryName();
    }

    public int getSportsCategoryID() {
        return sportsCategoryID;
    }

    public void setSportsCategoryID(int sportsCategoryID) {
        this.sportsCategoryID = sportsCategoryID;
    }

    public String getSportsCategoryName() {
        return sportsCategoryName;
    }

    public void setSportsCategoryName(String sportsCategoryName) {
        this.sportsCategoryName = sportsCategoryName;
    }
}
