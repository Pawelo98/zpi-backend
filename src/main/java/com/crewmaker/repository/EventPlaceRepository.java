package com.crewmaker.repository;

import com.crewmaker.entity.EventPlace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventPlaceRepository extends JpaRepository<EventPlace, Long> {

}
// Max results?
//List<Event> findByEventId(int id);

