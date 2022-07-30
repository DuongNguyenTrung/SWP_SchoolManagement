package com.swp.SchoolManagement.services;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swp.SchoolManagement.DTO.ClassDTO;
import com.swp.SchoolManagement.util.Util;

@Service
public class ClassService {
    @PersistenceContext
    EntityManager em;

    public List<ClassDTO> getClassByTeacherId(int teacherId) {
        String sql = "select * from Class c join TeacherClass tc on tc.class_id=c.class_id where teacher_id ="
                + teacherId;
        List<Object[]> re = em.createNativeQuery(sql).getResultList();
        return re.stream().map(e -> {
            return new ClassDTO(Util.parseInt(e[0]), Util.parseString(e[1]));
        }).collect(Collectors.toList());
    }
}
