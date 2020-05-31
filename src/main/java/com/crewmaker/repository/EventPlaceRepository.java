package com.crewmaker.repository;

import com.crewmaker.dto.EventPlaceDTO;
import com.crewmaker.entity.EventPlace;
import com.crewmaker.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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
    @Query("SELECT new com.crewmaker.dto.EventPlaceDTO(ep.eventPlaceId, ep.name, ep.city, " +
            "ep.postCode, ep.street, ep.streetNumber) " +
            "FROM EventPlace ep " +
            "JOIN ep.eventPlaceSportsCategories epsc " +
            "WHERE ep.city = :eventCity AND epsc.id.sportsCategory.sportsCategoryId = :sportCategoryId")
    List<EventPlaceDTO> findEventPlaceByCityAndSportCategory(@Param("sportCategoryId") int sportCategoryId, @Param("eventCity") String eventCity);
}