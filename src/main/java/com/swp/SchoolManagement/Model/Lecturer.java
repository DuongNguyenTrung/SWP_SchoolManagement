package com.swp.SchoolManagement.Model;

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
    private Long LecturerID;
    private Long LecturerInCourseID;
    private String LecturerName;
    private String LecturerAvatar;
    private String Dob;
    private Long LecturerNumber;
    private String LecturerEmail;
    private String LecturerAddress;
    private String LecturerSkillLevel;
}