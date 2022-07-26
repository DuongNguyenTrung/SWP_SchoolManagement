package com.swp.SchoolManagement.services;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.swp.SchoolManagement.config.exception.GlobalExeption;
import com.swp.SchoolManagement.model.Account;
import com.swp.SchoolManagement.repository.AccountRepository;
import com.swp.SchoolManagement.request.RegisterRequest;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;
    @PersistenceContext
    private EntityManager em;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public String register(RegisterRequest request) {
        Account a = new Account();
        a.setEmail(request.email);
        a.setPassword(bCryptPasswordEncoder.encode(request.password));
        a.setRole(request.role);
        Account res = accountRepository.save(a);
        if (res == null) {
            throw new GlobalExeption(404, "fail to create account with email: " + request.email);
        }
        return request.email;

    }

}
