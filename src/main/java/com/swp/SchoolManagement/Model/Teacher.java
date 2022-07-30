package com.swp.SchoolManagement.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "teacher_id")
    private long teacherId;
    @Column(name = "fullname")
    private String fullname;
    @Column(name = "address")
    private String address;
    @Column(name = "rank")
    private String rank;
    @Column(name = "avatar")
    private String avatar;
}
