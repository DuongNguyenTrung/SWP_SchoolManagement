package com.swp.SchoolManagement.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long CourseID;
    private String SubjectID;
    private String LecturerID;
    private String CentureID;
    private String StudentID;
    private String JoinDate;
    private String EndDate;
}
