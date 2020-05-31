package com.crewmaker.repository;

import com.crewmaker.entity.EventPlace;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventPlaceRepository extends JpaRepository<EventPlace, Integer> {
    Page<EventPlace> findAllByIsAcceptedIsFalse(Pageable pageable);
    Page<EventPlace> findAllByIsAcceptedIsTrue(Pageable pageable);
    Page<EventPlace> findAllByIsArchivedIsFalse(Pageable pageable);
    Page<EventPlace> findAllByIsArchivedIsTrue(Pageable pageable);  
    EventPlace findByEventPlaceId(int id);
    List<EventPlace> findAll();
    List<EventPlace> findTop20ByIsAcceptedIsTrue();
}