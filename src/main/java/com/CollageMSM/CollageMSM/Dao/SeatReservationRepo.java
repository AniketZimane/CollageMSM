package com.CollageMSM.CollageMSM.Dao;

import com.CollageMSM.CollageMSM.Entity.SeatReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface SeatReservationRepo extends JpaRepository<SeatReservation,Integer> {
}
