package com.crewmaker.repository;

import com.crewmaker.entity.EventPlace;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventPlaceRepository extends JpaRepository<EventPlace,Long> {
    EventPlace findByEventPlaceId(int id);
}
