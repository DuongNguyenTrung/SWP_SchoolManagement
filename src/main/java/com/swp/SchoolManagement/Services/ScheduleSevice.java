package com.swp.SchoolManagement.services;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.swp.SchoolManagement.DTO.ScheduleDTO;
import com.swp.SchoolManagement.util.Util;

@Service
@Transactional
public class ScheduleSevice {
    
    @PersistenceContext(name = "primaryEM")
    EntityManager entityManager;
    
    public Map<String,List<ScheduleDTO>> ScheduleByStudent(String fromDate,String toDate,int studentId){
        String sql = "select s.student_code,s.student_name,t.subject_id,d.slot_id,c.class_name,d.day_id  "+
		"from Student s join StudentClass sc on s.student_id = sc.student_id  "+
		"join Class c on c.class_id = sc.class_id "+
		"join Timetable t on t.class_id = c.class_id  "+
		"join DaySlot d on d.id = t.day_slot_id  "+
		"where d.day_id between :fromDate and :toDate and s.student_id = :studentId ";

        StringBuilder strBuilderQuery = new StringBuilder(sql);
        Query query = entityManager.createNativeQuery(strBuilderQuery.toString());

        if(!fromDate.isEmpty()){
            query.setParameter(":fromDate", "'"+fromDate+"'");
        }

        if(!toDate.isEmpty()){
            query.setParameter(":toDate",  "'"+toDate+"'");
        }

        if(Util.parseString(studentId)!=null){
            query.setParameter(":studentId", studentId);
        }
        

        List<Object[]> object = query.getResultList(); 

        List<ScheduleDTO> list = object.stream().map(obj->{
            ScheduleDTO dto = new ScheduleDTO();
                        dto.setStudentCode(Util.parseString(obj[0]));
                        dto.setName(Util.parseString(obj[1]));
                        dto.setSubjectName(Util.parseString(obj[2]));
                        dto.setSlot(Util.parseInt(obj[3]));
                        dto.setClassName(Util.parseString(obj[4]));
                        dto.setDayId(Util.parseString(obj[5]));
                        return dto;
        }).collect(Collectors.toList());

        Map<String,List<ScheduleDTO>> schedule  = list.stream().collect(Collectors.groupingBy(ScheduleDTO::getDayId));
        return schedule;
    }
}
