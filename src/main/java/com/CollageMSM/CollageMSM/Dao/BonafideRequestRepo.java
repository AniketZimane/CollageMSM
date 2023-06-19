package com.CollageMSM.CollageMSM.Dao;

import com.CollageMSM.CollageMSM.Entity.BonafideRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface BonafideRequestRepo extends JpaRepository<BonafideRequest,Integer> {
}
