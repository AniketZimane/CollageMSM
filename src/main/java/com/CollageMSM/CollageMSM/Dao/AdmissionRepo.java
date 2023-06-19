package com.CollageMSM.CollageMSM.Dao;

import com.CollageMSM.CollageMSM.Entity.Admission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface AdmissionRepo extends JpaRepository<Admission,Integer> {
    List<Admission> findByEmailid(String emailid);
    List<Admission> findByMobileno(String mobileno);
//    @Query("SELECT from Admission WHERE id=:id AND status=:status")
//    List<Admission> findByIdAndStatus(Integer id,String status);
}
