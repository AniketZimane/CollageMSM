package com.CollageMSM.CollageMSM.Dao;

import com.CollageMSM.CollageMSM.Entity.ImportantNotification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImportantDateRepo extends JpaRepository<ImportantNotification,Integer> {
}
