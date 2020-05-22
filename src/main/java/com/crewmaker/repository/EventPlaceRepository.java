package com.crewmaker.repository;

import com.crewmaker.entity.EventPlace;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventPlaceRepository extends JpaRepository<EventPlace, Integer> {
    Page<EventPlace> findAllByIsAcceptedIsFalse(Pageable pageable);
    Page<EventPlace> findAllByIsAcceptedIsTrue(Pageable pageable);
    Page<EventPlace> findAllByIsArchivedIsFalse(Pageable pageable);
    Page<EventPlace> findAllByIsArchivedIsTrue(Pageable pageable);  
    EventPlace findByEventPlaceId(int id);
}