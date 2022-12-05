package com.damir.healthcare.auth;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;
import java.util.Optional;

public interface AuGroupRepository extends JpaRepository<AuGroup, Integer> {
    ArrayList<AuGroup> findAllByEmail(String email);
    @Query(nativeQuery = true, value = "Select * from roles r where r.email=:email AND r.role=:role")
    Optional<AuGroup> native_findGroup(String email, String role);
    void deleteByEmail(String email);
    Optional<AuGroup> findFirstByEmail(String email);

}
