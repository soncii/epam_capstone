package com.damir.healthcare.repositories;

import com.damir.healthcare.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;



public interface UserRepository extends JpaRepository<User,String> {

    Page<User> findAllByOrderByIdAsc(Pageable pageable);

    @Query(value = "SELECT email,password,name,surname,salary,phone,cname from users u natural join doctor d order by email",
            countQuery = "SELECT count(*) FROM users u natural join doctor d"
            ,nativeQuery = true)
    Page<User> findAllDoctors(Pageable pageable);


    @Query(value = "SELECT email,password,name,surname,salary,phone,cname from users u natural join publicservant ps order by email",
            countQuery = "SELECT count(*) FROM users u natural join publicservant ps"
            ,nativeQuery = true)
    Page<User> findAllStatisticians(Pageable pageable);
}
