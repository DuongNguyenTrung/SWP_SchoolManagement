package com.swp.SchoolManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

import com.swp.SchoolManagement.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long>{
    String sql = 
    "select s.student_code,s.student_name,t.subject_id,d.slot_id,c.class_name,d.day_id   "+
    "from Student s join StudentClass sc on s.student_id = sc.student_id  "+
    "join Class c on c.class_id = sc.class_id "+
    "join Timetable t on t.class_id = c.class_id  "+
    "join Dayslot d on d.id = t.day_slot_id  "+
    "where d.day_id between :fromDate and :toDate and s.student_id = :studentId order by d.slot_id asc ";

    
 @Query(value = sql, nativeQuery = true)
 List<Object[]> findScheduleByStudent(@PathVariable String fromDate ,@PathVariable String toDate,@PathVariable int studentId);
}