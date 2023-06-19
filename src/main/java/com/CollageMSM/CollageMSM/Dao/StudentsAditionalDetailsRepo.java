package com.CollageMSM.CollageMSM.Dao;

import com.CollageMSM.CollageMSM.Entity.StudentsAditionalDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface StudentsAditionalDetailsRepo extends JpaRepository<StudentsAditionalDetails,Integer> {
    StudentsAditionalDetails findByStudId(String studId);
}
