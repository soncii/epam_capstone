package com.damir.healthcare.repositories;

import com.damir.healthcare.entities.Doctor;
import com.damir.healthcare.entities.Publicservant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PublicServantRepository extends JpaRepository<Publicservant, String> {
    public List<Publicservant> findAllByOrderByEmailAsc();
    @Query(value = "SELECT email, department from users u natural join publicservant ps order by email",
            countQuery = "SELECT count(*) FROM users u natural join publicservant ps"
            ,nativeQuery = true)
    Page<Publicservant> findAllPublicServants(Pageable pageable);
}
