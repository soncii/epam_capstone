package com.damir.healthcare.repositories;

import com.damir.healthcare.entities.Reviewing;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReviewingRepository extends JpaRepository<Reviewing,Integer> {
    Optional<Reviewing> findByOriginalIdAndAction(Integer id, String action);
}
