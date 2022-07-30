package com.swp.SchoolManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.swp.SchoolManagement.model.Subject;

public interface SubjectRepository extends JpaRepository<Subject,Long>{
    
}
