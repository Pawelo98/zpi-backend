package com.crewmaker.repository;

import com.crewmaker.entity.Event;
import com.crewmaker.entity.Participation;
import com.crewmaker.entity.User;
import com.crewmaker.model.UserProfile.UserProfileUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParticipationRepository extends JpaRepository<Participation,Long> {
    Boolean existsByIdEventAndIdUser(Event event, User user);

    @Query("SELECT new com.crewmaker.model.UserProfile.UserProfileUser(u.username, u.email, u.phoneNumber, u.photoLink, u.description) " +
            "Event e where e.eventId = :eventId, Participation p, User u ")
    List<UserProfileUser> findParticipatorsOfEvent(int eventId);
}
