package com.crewmaker.repository;

import com.crewmaker.entity.User;
import com.crewmaker.model.UserProfile.UserProfileUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    @Query("SELECT new com.crewmaker.model.UserProfile.UserProfileUser(u.username, u.email, u.phoneNumber, u.photoLink, u.description) " +
            "FROM User u where u.username = :username")
    UserProfileUser findByUsernameUserProfile(@Param("username") String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}
