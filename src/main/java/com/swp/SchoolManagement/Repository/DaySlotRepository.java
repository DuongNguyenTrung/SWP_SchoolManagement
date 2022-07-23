package com.swp.SchoolManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.swp.SchoolManagement.model.DaySlot;

public interface DaySlotRepository extends JpaRepository<DaySlot, Long> {
    
}
