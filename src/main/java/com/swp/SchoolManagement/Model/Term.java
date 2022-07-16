package com.swp.SchoolManagement.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Term {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int term_id;
    String term_name;
    String term_start;
    String term_end;
}
