package com.CollageMSM.CollageMSM.Dao;

import com.CollageMSM.CollageMSM.Entity.CutOff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface CutoffRepo extends JpaRepository<CutOff, Integer> {

}
