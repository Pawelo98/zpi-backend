package com.crewmaker.repository;

import com.crewmaker.entity.UserOpinion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserOpinionRepository extends JpaRepository<UserOpinion,Long> {

    List<UserOpinion> findAllByUserAboutUsernameAndUserAuthorUsernameNot(String username, String current);

    UserOpinion findByUserAboutUsernameAndUserAuthorUsername(String username, String current);
}
