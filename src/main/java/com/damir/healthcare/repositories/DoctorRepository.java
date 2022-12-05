package com.damir.healthcare.repositories;

import com.damir.healthcare.entities.Country;
import com.damir.healthcare.entities.Doctor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DoctorRepository extends JpaRepository<Doctor,String> {
    public List<Doctor> findAllByOrderByEmailAsc();

    @Query(value = "SELECT email,degree from users u natural join doctor d order by email",
            countQuery = "SELECT count(*) FROM users u natural join doctor d"
            ,nativeQuery = true)
    Page<Doctor> findAllDoctorsValues(Pageable pageable);
}
