package com.swp.SchoolManagement.services;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.swp.SchoolManagement.DTO.ScheduleDTO;
import com.swp.SchoolManagement.repository.StudentRepository;
import com.swp.SchoolManagement.util.Util;

@Service
@Transactional
public class ScheduleSevice {
    
    @Autowired
    StudentRepository repository;
    @PersistenceContext(name = "primaryEM")
    EntityManager entityManager;
    
    public Map<String,List<ScheduleDTO>> ScheduleByStudent(String fromDate,String toDate,int studentId){
    
        
        List<Object[]> object = repository.findScheduleBySrudent(fromDate,toDate,studentId); 

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
