package com.swp.SchoolManagement.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Attendence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long AttendanceID;
    private Long SlotID;
    private Long StudentInCourseID;
    private String AttendanceStatus;
    private String AttendanceDate;
}
