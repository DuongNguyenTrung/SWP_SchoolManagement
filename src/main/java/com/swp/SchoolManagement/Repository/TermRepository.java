package com.swp.SchoolManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.swp.SchoolManagement.model.Term;

@Repository
public interface TermRepository extends JpaRepository<Term, Integer> {

}
