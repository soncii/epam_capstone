package com.damir.healthcare.repositories;

import com.damir.healthcare.entities.Doctor;
import com.damir.healthcare.entities.RecordID;
import com.damir.healthcare.entities.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RecordRepository extends JpaRepository<Record, RecordID> {
    public List<Record> findAllByOrderByTotalPatientsAsc();
    @Modifying
    @Query(value="Delete from Record d where d.totalPatients=:i ")
    void delete(Integer i);
    @Modifying
    @Query(value = "update Record set disease_code=:newDisCode where disease_code=:oldDisCode")
    void updateDisCode(String oldDisCode, String newDisCode);
}
