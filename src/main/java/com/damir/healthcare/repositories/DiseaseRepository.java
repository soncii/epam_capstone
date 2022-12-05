package com.damir.healthcare.repositories;

import com.damir.healthcare.entities.Country;
import com.damir.healthcare.entities.Disease;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface DiseaseRepository extends JpaRepository<Disease, Integer> {
    Optional<Disease> findByDiseaseCode(String diseaseCode);
    public List<Disease> findAllByOrderByDiseaseCode();
    @Query(value = "SELECT disease_code from Disease d LEFT JOIN Specialize s on s.id=d.id  where s.email=:email", nativeQuery = true)
    List<String> native_findByid(String email);


    Page<Disease> findAllByAccreditedIsTrueOrderByDiseaseCode(Pageable pageable);

    Page<Disease> findAllByAccreditedIsFalseOrderByDiseaseCode(Pageable pageable);


    void deleteByDiseaseCode(String disease_code);


}
