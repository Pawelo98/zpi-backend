package com.crewmaker.repository;

import com.crewmaker.entity.CyclePeriod;
import com.crewmaker.entity.EventStatus;
import com.crewmaker.entity.SportsCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventStatusRepository extends JpaRepository<EventStatus,Long> {
    EventStatus findByEventStatusId(int id);
}
