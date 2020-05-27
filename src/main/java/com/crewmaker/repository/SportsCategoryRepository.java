package com.crewmaker.repository;

import com.crewmaker.dto.SportsCategoryDTO;
import com.crewmaker.entity.CyclePeriod;
import com.crewmaker.entity.SportsCategory;
import com.crewmaker.model.UserProfile.UserProfileUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SportsCategoryRepository extends JpaRepository<SportsCategory,Integer> {
    @Query("SELECT new com.crewmaker.dto.SportsCategoryDTO(sc.sportsCategoryId, sc.sportCategoryName) " +
            "FROM SportsCategory sc " +
            "JOIN sc.sportsCategoryEventPlaces scep " +
            "JOIN scep.id.eventPlace ep " +
            "WHERE ep.eventPlaceId = :eventPlaceID")
    /*@Query("SELECT new com.crewmaker.dto.SportsCategoryDTO(sc.sportsCategoryId, sc.sportCategoryName) " +
            "FROM SportsCategory sc " +
            "WHERE sc.sportsCategoryEventPlaces.id.eventPlace.eventPlaceId = :eventPlaceID")*/
    List<SportsCategoryDTO> findByEventPlaceId(@Param("eventPlaceID") int eventPlaceID);

    SportsCategory findBySportsCategoryId(int id);
}
