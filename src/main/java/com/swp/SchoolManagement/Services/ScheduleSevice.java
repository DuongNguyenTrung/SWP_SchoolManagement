package com.swp.SchoolManagement.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import javax.persistence.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.swp.SchoolManagement.DTO.AttendStudent;
import com.swp.SchoolManagement.DTO.ScheduleDTO;
import com.swp.SchoolManagement.repository.ClassRepository;
import com.swp.SchoolManagement.repository.StudentRepository;
import com.swp.SchoolManagement.repository.TeacherRepository;
import com.swp.SchoolManagement.response.ListClass;
import com.swp.SchoolManagement.util.Util;

@Service
@Transactional
public class ScheduleSevice {

    @Autowired
    StudentRepository repository;

    @Autowired
    TeacherRepository repository1;

    @Autowired
    ClassRepository repository2;
            
    @PersistenceContext()
    EntityManager entityManager;

    public List<Map<String,ScheduleDTO>> ScheduleByStudent(String fromDate, String toDate, int studentId) {

        List<Object[]> object = repository.findScheduleByStudent(fromDate, toDate, studentId);

        List<ScheduleDTO> list = object.stream().map(obj -> {
            ScheduleDTO dto = new ScheduleDTO();
            dto.setSubjectName(Util.parseString(obj[0]));
            dto.setSlot(Util.parseInt(obj[1]));
            dto.setTime(Util.parseString(obj[2]));
            dto.setStatusAttend(Util.parseInt(obj[3]));
            dto.setClassName(Util.parseString(obj[4]));
            dto.setDayId(Util.parseString(obj[5]));
            dto.setWeekday(Util.parseString(obj[6]));
            return dto;
        }).collect(Collectors.toList());

        Map<Integer, List<ScheduleDTO>> map = list.stream().collect(Collectors.groupingBy(ScheduleDTO::getSlot));
        List<Map<String,ScheduleDTO>> res = new ArrayList<>();
        String[] lsday = { "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday" };
        for (int i = 1; i <= 8; i++) {
            if (map.containsKey(i)) {
                Map<String,ScheduleDTO> map2 = map.get(i).stream().collect(Collectors.toMap(ScheduleDTO::getWeekday,Function.identity() ));
                for (int j = 0; j < lsday.length; j++) {
                    if(!map2.containsKey(lsday[j]))
                        map2.put(lsday[j], null);      
                }
                res.add(map2);
            } else {
                res.add(null);
            }
        }
        return res;
    }
    
    public List<Object> listSubject(int teacherId){
        return repository1.getListSubject(teacherId);    
    }

    public List<ListClass> listClass(int teacherId,String subjectID){
        List<Object[]> list = repository1.getListClass(teacherId, subjectID);
        return list.stream().map(obj->{
            ListClass l = new ListClass();
            l.setId(Util.parseInt(obj[0]));
            l.setClassName(Util.parseString(obj[1]));
            return l;
        }).collect(Collectors.toList());
    }
    
    public List<AttendStudent> getAttendStudent(int classId,String subjectId,String fromDate){
       List<Object[]> list = repository1.getAttendStudent(classId, subjectId, fromDate);
       List<AttendStudent> res =  list.stream().map(obj ->{
            AttendStudent a = new AttendStudent();
                        a.setStudentCode(Util.parseString(obj[0]));
                        a.setFullname(Util.parseString(obj[1]));
                        a.setAvatar(Util.parseString(obj[2]));
                        a.setStatus(Util.parseInt(obj[3]));
                        a.setAttendId(Util.parseLong(obj[4]));
                        return a;
       }).collect(Collectors.toList());
       return res;
    }
    public void updateAttend(Integer status,Integer attendId){
        String sql = "update Attendance set  status  = :status where id = :attendId";

        StringBuilder strBuilderQuery = new StringBuilder(sql);
        Query query = entityManager.createNativeQuery(strBuilderQuery.toString());
        if(status != null){
            query.setParameter("status",status);
        }
        if(attendId != null){
            query.setParameter("attendId",attendId);
        }
        query.executeUpdate();
    }
    public  List<Object> getClassList(String subjectId){
        List<Object[]> list = repository2.getClassList(subjectId);
        return list.stream().map(obj->{
            ListClass l = new ListClass();
            l.setId(Util.parseInt(obj[0]));
            l.setClassName(Util.parseString(obj[1]));
            return l;
        }).collect(Collectors.toList());
    }
    public void insertStudentClass(Integer studentId,Integer classId,String subjectId){
        String sql = "INSERT INTO StudentClass(student_id,class_id) VALUES (:studentId,:classId); "+
        "insert into Attendance(timetable_id,day_slot_id,student_id,status) "+
        "select timetable_id,day_slot_id,:studentId,2 from Timetable where class_id= :classId and subject_id= :subjectId";

        StringBuilder strBuilderQuery = new StringBuilder(sql);
        Query query = entityManager.createNativeQuery(strBuilderQuery.toString());
        if(studentId != null){
            query.setParameter("studentId",studentId);
        }
        if(classId != null){
            query.setParameter("classId",classId);
        }
        if(subjectId != null){
            query.setParameter("subjectId",subjectId);
        }
        query.executeUpdate();
    }
}
