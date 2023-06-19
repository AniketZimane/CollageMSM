package com.CollageMSM.CollageMSM.Dao;

import com.CollageMSM.CollageMSM.Entity.FeesCriteria;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface FeesCriteriaRepo extends JpaRepository<FeesCriteria, Integer> {
}
