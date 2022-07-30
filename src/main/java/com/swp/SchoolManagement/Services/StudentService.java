package com.swp.SchoolManagement.services;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.swp.SchoolManagement.repository.StudentRepository;

public class StudentService {
    @PersistenceContext
    EntityManager em;
    @Autowired
    StudentRepository studentRepo;
    
}
