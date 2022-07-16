package com.swp.SchoolManagement.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long student_id;
    private String student_name;
    private String student_code;
    private String email;
    private String phone;
    private String address;
    private String avatar;
    
}
