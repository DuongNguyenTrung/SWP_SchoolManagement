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
@Table(name = "Class")
public class Class {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int class_id;
    @Column(name ="class_name" )
    String class_name;
    @Column(name ="term_id" )
    int term_id;
}
