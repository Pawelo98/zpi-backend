package com.crewmaker.repository;

import com.crewmaker.entity.UserProfileImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EventPlaceImageRepository extends JpaRepository<UserProfileImage, Long> {

    @Modifying
    @Query("update EventPlaceImage u set u.name = ?1, u.type = ?2, u.binaryData = ?3 where u.eventPlaceImageID = ?4")
    void setEventPlaceImageById(String name, String type, byte[] binaryData, Long eventPlaceImageID);
}
