package com.swp.SchoolManagement.controller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.swp.SchoolManagement.DTO.ClassDTO;
import com.swp.SchoolManagement.services.ClassService;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/api/teacher")
public class ControllerTeacher {

    @Autowired
    ClassService classService;

    @GetMapping("/getClass")
    public List<ClassDTO> getClassByTeacherId(
            @RequestParam int teacherId) {
        return classService.getClassByTeacherId(teacherId);

    }
}
