package com.crewmaker.repository;

import com.crewmaker.entity.Event;
import com.crewmaker.entity.SportsCategory;
import com.crewmaker.model.UserProfile.UserProfileEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    // Max results?
    @Query("SELECT new com.crewmaker.model.UserProfile.UserProfileEvent(sc.sportCategoryName, e.date, e.eventTime, " +
            "ep.name, ep.city, ep.street, ep.streetNumber, " +
            "(SELECT Count(p2.id.event) FROM Participation p2 WHERE p2.id.event.eventId = e.eventId), e.maxPlayers) " +
            "FROM Event e " +
            "JOIN e.sportsCategory as sc " +
            "JOIN e.eventPlace as ep " +
            "JOIN e.eventParticipations as p " +
            "JOIN p.id.user as u " +
            "WHERE u.username = :username " +
            "GROUP BY e.eventId " +
            "ORDER BY e.date DESC")
    Collection<UserProfileEvent> findPastEventsByUsername(@Param("username") String username);

    /*@Query("SELECT new com.crewmaker.model.UserProfile.UserProfileEvent(sc.sportCategoryName, e.date, e.eventTime, " +
            "ep.name, ep.city, ep.street, ep.streetNumber, " +
            "(SELECT Count(p2.id.event) FROM Participation p2 WHERE p2.id.event = e.eventId), e.maxPlayers) " +
            "FROM Event e " +
            "JOIN e.sportsCategory as sc " +
            "JOIN e.eventPlace as ep " +
            "JOIN e.eventParticipations as p " +
            "JOIN p.id.user as u " +
            "WHERE u.username = :username AND e.date < NOW() " +
            "GROUP BY e.eventId " +
            "ORDER BY e.date DESC ")
    UserProfileEvent findFutureEventsByUsername(@Param("username") String username);*/
}
