package com.crewmaker.repository;

import com.crewmaker.entity.CyclePeriod;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CyclePeriodRepository extends JpaRepository<CyclePeriod,Long> {
    CyclePeriod findByCyclePeriodId(int id);
}
