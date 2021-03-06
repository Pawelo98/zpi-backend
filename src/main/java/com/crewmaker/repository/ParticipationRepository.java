package com.crewmaker.repository;

import com.crewmaker.entity.Event;
import com.crewmaker.entity.Participation;
import com.crewmaker.entity.User;
import com.crewmaker.model.UserProfile.UserProfileUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParticipationRepository extends JpaRepository<Participation,Long> {
    Boolean existsByIdEventAndIdUser(Event event, User user);

    Participation findByIdEventAndIdUser(Event event, User user);

    @Query("SELECT new com.crewmaker.model.UserProfile.UserProfileUser(u.username, u.email, u.phoneNumber, " +
            "u.description, u.name, u.surname) " +
            "FROM Event e, Participation p, User u WHERE e.eventId = :eventID")
    List<UserProfileUser> findParticipatorsOfEvent(@Param("eventID") int eventID);
}
