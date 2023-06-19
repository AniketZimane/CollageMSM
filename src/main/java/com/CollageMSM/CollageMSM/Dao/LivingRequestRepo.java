package com.CollageMSM.CollageMSM.Dao;

import com.CollageMSM.CollageMSM.Entity.BonafideRequest;
import com.CollageMSM.CollageMSM.Entity.LivingRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface LivingRequestRepo extends JpaRepository<LivingRequest, Integer> {
    List<LivingRequest> findByIdAndStatus(Integer studentId, String status);

    List<LivingRequest> findByStudentId(String studentId);
}
