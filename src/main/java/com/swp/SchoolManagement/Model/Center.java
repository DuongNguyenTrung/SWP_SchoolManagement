package com.swp.SchoolManagement.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Center {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long CenterID;
    private String CourseID;
    private String CenterName;
    private String CenterAddress;
}