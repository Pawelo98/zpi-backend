package com.crewmaker.repository;

import com.crewmaker.entity.SportsCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SportsCategoryRepository extends JpaRepository<SportsCategory,Long> {
}
