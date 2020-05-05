package com.crewmaker.repository;


import com.crewmaker.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.sql.Time;
import java.util.Date;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event,Long> {
    List<Event> findAllBySportsCategorySportCategoryName(String SportCategoryName);
    List<Event> findBySportsCategorySportCategoryName(String SportCategoryName);
    List<Event> findAllBySportsCategorySportsCategoryId(int SportCategoryID);
    List<Event> findAllByDateAfterAndAndSportsCategorySportsCategoryIdOrderByDate
            (Date eventDate, int SportCategoryID);
    List<Event> findAllByDateAfterAndEventTimeAfterAndSportsCategorySportsCategoryIdOrderByDateAscEventTimeAsc(
            Date eventDate, Time eventTime, int SportCategoryID
    );
    Event findByEventId(int id);
}
