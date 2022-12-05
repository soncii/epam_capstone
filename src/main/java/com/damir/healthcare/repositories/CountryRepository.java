package com.damir.healthcare.repositories;

import com.damir.healthcare.entities.Country;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.lang.annotation.Native;
import java.util.List;

public interface CountryRepository extends JpaRepository<Country,String> {
     List<Country> findAllByOrderByIdAsc();
    Page<Country> findAllByOrderByIdAsc(Pageable pageable);

}
