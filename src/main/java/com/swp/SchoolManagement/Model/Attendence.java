package com.swp.SchoolManagement.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "Attendence")
public class Attendence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    private Long id;
    @Column(name = "timetable_id")
    private Long timetable_id;

    @Column(name = "day_slot_id")
    private Long day_slot_id;

    @Column(name = "student_id")
    private Long student_id;

    @Column(name = "status")
    private String status;
}
