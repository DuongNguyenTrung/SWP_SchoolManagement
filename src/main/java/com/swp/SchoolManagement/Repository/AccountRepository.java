package com.swp.SchoolManagement.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.swp.SchoolManagement.Model.Account;
@Repository
public interface AccountRepository extends JpaRepository<Account,Long> {
    
}
