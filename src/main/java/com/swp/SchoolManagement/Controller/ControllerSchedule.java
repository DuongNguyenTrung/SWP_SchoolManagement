package com.swp.SchoolManagement.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.swp.SchoolManagement.DTO.AttendStudent;
import com.swp.SchoolManagement.DTO.ScheduleDTO;
import com.swp.SchoolManagement.response.ListClass;
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
    public List<ListClass> getListClass(
            @RequestParam(name = "teacherId", required = true) int teacherId,
            @RequestParam(name = "subjectId", required = true) String subjectId) {
                List<ListClass> list = scheduleSevice.listClass(teacherId, subjectId);
                return list;
    }
    @GetMapping("/listAttendStudent")
    public  List<AttendStudent> listAttendStudent(
            @RequestParam(name = "classId", required = true) int classId,
            @RequestParam(name = "subjectId", required = true) String subjectId,
            @RequestParam(name = "fromDate", required = true) String fromDate) {
            List<AttendStudent> list = scheduleSevice.getAttendStudent(classId, subjectId, fromDate);
                return list;
    }
    @PutMapping("/updateAttend")
    public void updateAttend(
            @RequestParam(name = "status", required = true) Integer status,
            @RequestParam(name = "attendId", required = true) Integer attendId) {
                    scheduleSevice.updateAttend(status, attendId);
    }
    @GetMapping("/Class-Student choose")
    public  List<Object> listClass(
        @RequestParam(name = "subjectId", required = true) String subjectId) {
        return scheduleSevice.getClassList(subjectId);

    }
    @PostMapping("/insert-Class-Student")
       public void insertClassStudent(
               @RequestParam(name = "studentId", required = true) Integer studentId,
               @RequestParam(name = "classId", required = true) Integer classId,
               @RequestParam(name = "subjectId", required = true) String subjectId) {
                       scheduleSevice.insertStudentClass(studentId, classId,subjectId);;
       }
    }
    

