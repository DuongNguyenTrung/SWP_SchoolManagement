package com.swp.SchoolManagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.swp.SchoolManagement.model.Class;

@Repository
public interface ClassRepository extends JpaRepository<Class, Long>{
    String sql = "select distinct( c.id),c.class_name from Timetable t "+
    "join Class c on c.class_id = t.class_id        "+
    "join DaySlot d on t.day_slot_id = d.id         "+
    "where subject_id = :subjectId   ";
   
    @Query(value = sql, nativeQuery = true)
    List<Object[]> getClassList(String subjectId);
}
