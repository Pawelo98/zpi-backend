DROP DATABASE IF EXISTS `CrewMaker`;
CREATE DATABASE `CrewMaker`;
USE `CrewMaker`;

CREATE TABLE `User` 
(
	UserID int NOT NULL AUTO_INCREMENT, 
	Login varchar(60) NOT NULL UNIQUE, 
    Email varchar(60) UNIQUE, 
    `Password` varchar(60) NOT NULL, 
    `Name` varchar(60) NOT NULL, 
    Surname varchar(60) NOT NULL, 
    Achrived bool NOT NULL, 
    PhoneNumber varchar(30), 
    IsAdmin bool NOT NULL, 
    PhotoLink varchar(128),
    UserDescription varchar(256),
    CONSTRAINT pk_User PRIMARY KEY (UserID)
) ENGINE=InnoDB;

CREATE TABLE EventPlace 
(
	EventPlaceID int NOT NULL AUTO_INCREMENT, 
    UserAcceptingID int NOT NULL, 
    UserRequestingID int NOT NULL, 
    EventPlaceName varchar(60) NOT NULL, 
    EventPlaceDescription varchar(255), 
    EventPlaceCity varchar(60) NOT NULL, 
    EventPlacePostCode varchar(20) NOT NULL, 
    EventPlaceStreet varchar(60) NOT NULL, 
    EventPlaceStreetNumber varchar(20) NOT NULL, 
    PhotoLink varchar(128),
    CONSTRAINT pk_EventPlace PRIMARY KEY (EventPlaceID),
    CONSTRAINT fk_eventPlaceUserRequestingID FOREIGN KEY (UserRequestingID) REFERENCES `User` (UserID),
    CONSTRAINT fk_eventPlaceUserAcceptingID FOREIGN KEY (UserAcceptingID) REFERENCES `User` (UserID)
) ENGINE=InnoDB;

CREATE TABLE UserOpinion 
(
	UserOpinionID int NOT NULL AUTO_INCREMENT, 
    UserAuthorID int NOT NULL, 
    UserAboutID int NOT NULL, 
    Title varchar(60) NOT NULL, 
    Message varchar(255), 
    Grade int NOT NULL CONSTRAINT con_userOpinionGrade CHECK (Grade>0), 
    CONSTRAINT pk_UserOpinion PRIMARY KEY (UserOpinionID),
    CONSTRAINT fk_userOpinionUserAboutID FOREIGN KEY (UserAboutID) REFERENCES `User` (UserID),
    CONSTRAINT fk_userOpinionUserAuthorID FOREIGN KEY (UserAuthorID) REFERENCES `User` (UserID)
) ENGINE=InnoDB;

CREATE TABLE EventPlaceOpinion 
(
	EventPlaceOpinionID int NOT NULL AUTO_INCREMENT, 
    EventPlaceID int NOT NULL, 
    UserID int NOT NULL, 
    Title varchar(60) NOT NULL, 
    Message varchar(255), 
    Grade int NOT NULL CONSTRAINT con_eventPlaceOpinionGrade CHECK (Grade>0),
    CONSTRAINT pk_EventPlaceOpinion PRIMARY KEY (EventPlaceOpinionID),
    CONSTRAINT fk_eventPlaceOpinionUserID FOREIGN KEY (UserID) REFERENCES `User` (UserID),
    CONSTRAINT fk_eventPlaceOpinionEventPlaceID FOREIGN KEY (EventPlaceID) REFERENCES EventPlace (EventPlaceID)
) ENGINE=InnoDB;

CREATE TABLE SportsCategory 
(
	SportsCategoryID int NOT NULL AUTO_INCREMENT, 
    SportCategoryName varchar(60) NOT NULL UNIQUE, 
    DefaultPlayersNumber int NOT NULL CONSTRAINT con_sportsCategoryDefaultPlayersNumber CHECK (DefaultPlayersNumber>0),
    CONSTRAINT pk_SportsCategory PRIMARY KEY (SportsCategoryID)
) ENGINE=InnoDB;

CREATE TABLE EventPlaceSportsCategory 
(
	SportsCategoryID int NOT NULL, 
    EventPlaceID int NOT NULL,
    MaxEventsSimultaneously int DEFAULT NULL,
    CONSTRAINT pk_EventPlaceSportsCategory PRIMARY KEY (SportsCategoryID, EventPlaceID),
    CONSTRAINT fk_eventPlaceSportsCategorySportsCategoryID FOREIGN KEY (SportsCategoryID) REFERENCES SportsCategory (SportsCategoryID),
    CONSTRAINT fk_eventPlaceSportsCategoryEventPlaceID FOREIGN KEY (EventPlaceID) REFERENCES EventPlace (EventPlaceID)
) ENGINE=InnoDB;

CREATE TABLE EventStatus 
(
	EventStatusID int NOT NULL AUTO_INCREMENT, 
    StatusName varchar(60) NOT NULL UNIQUE, 
    CONSTRAINT pk_EventStatus PRIMARY KEY (EventStatusID)
) ENGINE=InnoDB;

CREATE TABLE CyclePeriod 
(
	CyclePeriodID int NOT NULL AUTO_INCREMENT, 
    CycleType varchar(60) NOT NULL, 
    CycleLength int NOT NULL CONSTRAINT con_cycleLengthCyclePeriod CHECK (CycleLength>0), 
    CONSTRAINT pk_CyclePeriod PRIMARY KEY (CyclePeriodID)
) ENGINE=InnoDB;

CREATE TABLE `Event` 
(
	EventID int NOT NULL AUTO_INCREMENT, 
    CyclePeriodID int, 
    EventStatusID int NOT NULL, 
    UserID int NOT NULL, 
    EventPlaceID int NOT NULL, 
    SportsCategoryID int NOT NULL, 
    EventName varchar(60) NOT NULL, 
    EventDescription varchar(255), 
    EventDate date NOT NULL, 
    EventTime time NOT NULL, 
    EventDuration time NOT NULL, 
    EventMaxPlayers int NOT NULL CONSTRAINT con_eventMaxPlayersPositive CHECK (EventMaxPlayers>0), 
    IsCyclic bool NOT NULL, 
    CONSTRAINT pk_Event PRIMARY KEY (EventID),
    CONSTRAINT fk_eventUserID FOREIGN KEY (UserID) REFERENCES `User` (UserID),
    CONSTRAINT fk_eventEventStatusID FOREIGN KEY (EventStatusID) REFERENCES EventStatus (EventStatusID),
    CONSTRAINT fk_eventCyclePeriodID FOREIGN KEY (CyclePeriodID) REFERENCES CyclePeriod (CyclePeriodID),
    CONSTRAINT fk_eventEventPlaceID FOREIGN KEY (EventPlaceID) REFERENCES EventPlace (EventPlaceID),
    CONSTRAINT fk_eventSportsCategoryID FOREIGN KEY (SportsCategoryID) REFERENCES SportsCategory (SportsCategoryID)
) ENGINE=InnoDB;

CREATE TABLE Participation 
(
	UserID int NOT NULL, 
	EventID int NOT NULL, 
    QueuePosition int,
	CONSTRAINT pk_Participation PRIMARY KEY (UserID, EventID),
    CONSTRAINT fk_participationUserID FOREIGN KEY (UserID) REFERENCES `User` (UserID),
    CONSTRAINT fk_participationEventID FOREIGN KEY (EventID) REFERENCES `Event` (EventID)
) ENGINE=InnoDB;