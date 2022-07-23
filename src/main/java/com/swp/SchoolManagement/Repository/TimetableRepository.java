package com.swp.SchoolManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.swp.SchoolManagement.model.Timetable;

public interface TimetableRepository extends JpaRepository<Timetable, Long> {
    
}
