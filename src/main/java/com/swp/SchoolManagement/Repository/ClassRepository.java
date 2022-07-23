package com.swp.SchoolManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.swp.SchoolManagement.model.Class;

@Repository
public interface ClassRepository extends JpaRepository<Class, Long>{
    
}
