package com.swp.SchoolManagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.swp.SchoolManagement.model.Teacher;
@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    String sql  = "select distinct(o.subject_id ) "+
    "from Teacher t join TeacherClass tc on t.teacher_id = tc.teacher_id "+
    "join Timetable o on o.class_id = tc.class_id where t.teacher_id = :id ";
    @Query(value = sql, nativeQuery = true)
    List<Object> getListSubject(int id);

    String sql1 = "select  distinct(c.class_id) ,c.class_name  "+
    "from Teacher t join TeacherClass tc on t.teacher_id = tc.teacher_id "+
    "join Timetable o on o.class_id = tc.class_id  "+
    "join Class c on c.class_id = tc.class_id  "+
    "where t.teacher_id = :teacherId and o.subject_id =:subjectId  ";
    @Query(value = sql1, nativeQuery = true)
    List<Object[]> getListClass(int teacherId,String subjectId );

    String sql2 = "select s.student_code,s.fullname,s.avatar,a.status,a.id from Student s    "+
    "join Attendance a on a.student_id = s.student_id   "+
    "join Timetable t on t.timetable_id = a.timetable_id  "+
    "join DaySlot d on d.id = a.day_slot_id    "+
    "where t.class_id = :classId and t.subject_id = :subjectId and d.day_id = :fromDate";
    @Query(value = sql2, nativeQuery = true)
    List<Object[]> getAttendStudent( int classId,String subjectId,String fromDate );
}
