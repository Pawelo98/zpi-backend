package com.crewmaker.repository;

import com.crewmaker.entity.User;
import com.crewmaker.entity.UserProfileImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserProfileImageRepository extends JpaRepository<UserProfileImage, Long> {

    @Modifying
    @Query("update UserProfileImage u set u.name = ?1, u.type = ?2, u.binaryData = ?3 where u.imageId = ?4")
    void setUserProfileImageById(String name, String type, byte[] binaryData, Long imageId);
}
