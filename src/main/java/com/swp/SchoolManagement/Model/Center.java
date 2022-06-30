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
    private Long centerId;
    private String courseID;
    private String centerName;
    private String centerAddress;
}