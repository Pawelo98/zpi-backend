package com.crewmaker.repository;

import com.crewmaker.entity.EventPlaceOpinion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventPlaceOpinionRepository extends JpaRepository<EventPlaceOpinion,Long> {
    List<EventPlaceOpinion> findAllByEventPlaceEventPlaceId(int id);
}
