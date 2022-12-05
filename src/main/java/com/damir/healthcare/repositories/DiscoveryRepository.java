package com.damir.healthcare.repositories;

import com.damir.healthcare.entities.Country;
import com.damir.healthcare.entities.Discover;
import com.damir.healthcare.entities.DiscoverID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DiscoveryRepository extends JpaRepository<Discover, DiscoverID> {
    public List<Discover> findAllByOrderByIdAsc();
    Page<Discover> findAllByOrderByIdAsc(Pageable pageable);

    @Modifying
    @Query("update Discover set disease_code=:newCode where disease_code=:oldCode")
    void updateDisCode(String oldCode, String newCode);
}
