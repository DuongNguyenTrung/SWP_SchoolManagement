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
}
