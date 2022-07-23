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
@Table(name = "Term" )
public class Term {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int term_id;

    @Column(name = "term_name")
    String term_name;
    @Column(name = "term_start")
    String term_start;
    @Column(name = "term_end")
    String term_end;
}
