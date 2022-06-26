package com.swp.SchoolManagement.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long SubjectID;
    private Long CategoryID;
    private String SubjectName;
    private String SubjectCode;
    private String SubjectImage;
    private String SubjectDesc;
}