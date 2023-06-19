package com.CollageMSM.CollageMSM.Dao;

import com.CollageMSM.CollageMSM.Entity.Result;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface ResultRepo extends JpaRepository<Result,Integer> {
    List<Result> findByStudIdAndMotherName(String studId, String motherName);
}
