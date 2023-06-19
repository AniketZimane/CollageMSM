package com.CollageMSM.CollageMSM.Dao;

import com.CollageMSM.CollageMSM.Entity.BonafideRequest;
import com.CollageMSM.CollageMSM.Entity.LivingRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface BonafideRequestRepo extends JpaRepository<BonafideRequest, Integer> {
    List<BonafideRequest> findByIdAndStatus(Integer id, String status);

}
