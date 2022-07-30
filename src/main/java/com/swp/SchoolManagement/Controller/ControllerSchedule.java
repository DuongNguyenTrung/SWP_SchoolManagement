package com.swp.SchoolManagement.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.swp.SchoolManagement.DTO.AttendStudent;
import com.swp.SchoolManagement.DTO.ScheduleDTO;
import com.swp.SchoolManagement.services.ScheduleSevice;

import org.springframework.beans.factory.annotation.Autowired;


@RestController
@RequestMapping("/api")
public class ControllerSchedule {

    @Autowired
    ScheduleSevice scheduleSevice;

    @GetMapping("/scheduleByStudent")
    public List<Map<String,ScheduleDTO>> schedule(
            @RequestParam(name = "dateFrom", required = true) String dateFrom,
            @RequestParam(name = "dateTo", required = true) String dateTo,
            @RequestParam(name = "studentId", required = true) int studentId) {
                List<Map<String,ScheduleDTO>> map = scheduleSevice.ScheduleByStudent(dateFrom, dateTo, studentId);
                return map;
    }
    @GetMapping("/listSubject")
    public List<Object> getListSubject(
            @RequestParam(name = "teacherId", required = true) int teacherId) {
                List<Object> list = scheduleSevice.listSubject(teacherId);
                return list;
    }
    @GetMapping("/listClass")
    public List<Object> getListClass(
            @RequestParam(name = "teacherId", required = true) int teacherId,
            @RequestParam(name = "subjectId", required = true) String subjectId) {
                List<Object> list = scheduleSevice.listClass(teacherId, subjectId);
                return list;
    }
    @GetMapping("/listAttendStudent")
    public  List<AttendStudent> listAttendStudent(
            @RequestParam(name = "classId", required = true) int classId,
            @RequestParam(name = "subjectId", required = true) String subjectId,
            @RequestParam(name = "fromDate", required = true) String fromDate,
            @RequestParam(name = "toDate", required = true) String toDate) {
            List<AttendStudent> list = scheduleSevice.getAttendStudent(classId, subjectId, fromDate, toDate);
                return list;
    }
    @PutMapping("/updateAttend")
    public void updateAttend(
            @RequestParam(name = "status", required = true) Integer status,
            @RequestParam(name = "attendId", required = true) Integer attendId) {
                    scheduleSevice.updateAttend(status, attendId);
    }
    
    
}
