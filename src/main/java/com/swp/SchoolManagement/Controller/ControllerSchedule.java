package com.swp.SchoolManagement.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.swp.SchoolManagement.DTO.ScheduleDTO;
import com.swp.SchoolManagement.services.ScheduleSevice;

import org.springframework.beans.factory.annotation.Autowired;


@RestController
@RequestMapping("/api")
public class ControllerSchedule {

    @Autowired
    ScheduleSevice scheduleSevice;

    @GetMapping("/scheduleByStudent")
    public Map<String, List<ScheduleDTO>> schedule(
            @RequestParam(name = "dateFrom", required = true) String dateFrom,
            @RequestParam(name = "dateTo", required = true) String dateTo,
            @RequestParam(name = "studentId", required = true) int studentId) {
                Map<String, List<ScheduleDTO>> map = scheduleSevice.ScheduleByStudent(dateFrom, dateTo, studentId);
                return map;

    }
}
