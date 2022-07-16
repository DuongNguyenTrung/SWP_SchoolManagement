package com.swp.SchoolManagement.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Lecturer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private Long courseId;
    private String lecturerName;
    private String avatar;
    private String dob;
    private Long lecturerNumber;
    private String email;
    private String address;
    private String skillLevel;
}