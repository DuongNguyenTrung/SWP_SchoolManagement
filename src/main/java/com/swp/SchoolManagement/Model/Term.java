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
@Table(name = "Term")
public class Term {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "term_id")
    Integer termId;
    @Column(name = "term_name")
    String termName;
    @Column(name = "term_start")
    String termStart;
    @Column(name = "term_end")
    String termEnd;
    String year;
}
