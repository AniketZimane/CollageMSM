package com.CollageMSM.CollageMSM.Dao;

import com.CollageMSM.CollageMSM.Entity.Registration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface RegistrationRepo extends JpaRepository<Registration,Integer> {
    List<Registration> findByEmail(String email);
//    List<Registration> findByPassword(String password);
//    @Query("select email from Registration where email=:email")
//    String getEmail(String email);
//    @Query("select password from Registration where password=:password")
//    String getPassword(String password);
    Registration getByEmail(String email);



}
