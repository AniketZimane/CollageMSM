package com.CollageMSM.CollageMSM.Dao;

import com.CollageMSM.CollageMSM.Entity.LivingRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface LivingRequestRepo extends JpaRepository<LivingRequest,Integer> {
}
