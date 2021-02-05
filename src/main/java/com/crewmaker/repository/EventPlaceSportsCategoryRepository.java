package com.crewmaker.repository;

import com.crewmaker.entity.EventPlace;
import com.crewmaker.entity.EventPlaceSportsCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventPlaceSportsCategoryRepository extends JpaRepository<EventPlaceSportsCategory, Long> {

}