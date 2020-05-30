package com.crewmaker.repository;

import com.crewmaker.entity.Event;
import com.crewmaker.entity.Participation;
import com.crewmaker.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.util.Date;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    // Max results?
    //List<Event> findByEventId(int id);
    Event findByEventId(int id);

    List<Event> findAllBySportsCategorySportCategoryName(String SportCategoryName);
    List<Event> findBySportsCategorySportCategoryName(String SportCategoryName);
    List<Event> findAllBySportsCategorySportsCategoryId(int SportCategoryID);
    List<Event> findAllByEventParticipationsIdUser(User user);
    Long countAllByEventParticipationsIdEvent(Event event);
    List<Event> findAllByDateAfterAndAndSportsCategorySportsCategoryIdOrderByDate
            (Date date, int SportCategoryID);
    List<Event> findAllByDateAfterAndEventTimeAfterAndSportsCategorySportsCategoryIdOrderByDateAscEventTimeAsc(
            Date eventDate, Time eventTime, int SportCategoryID
    );
    List<Event> findAllByDateAfterAndEventTimeAfterAndSportsCategorySportsCategoryIdAndEventPlaceCityOrderByDateAscEventTimeAsc(Date eventDate,
                                                                                                                                          Time eventTime, int SportCategoryID,
                                                                                                                                          String eventPlaceCity);

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

