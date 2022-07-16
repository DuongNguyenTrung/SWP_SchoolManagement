package com.swp.SchoolManagement.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Class {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int class_id;
    String class_name;
    int term_id;
}
