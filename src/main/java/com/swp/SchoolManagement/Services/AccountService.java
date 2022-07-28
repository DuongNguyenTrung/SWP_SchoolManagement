package com.swp.SchoolManagement.services;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.swp.SchoolManagement.DTO.EmailDetails;
import com.swp.SchoolManagement.config.exception.GlobalExeption;
import com.swp.SchoolManagement.model.Account;
import com.swp.SchoolManagement.repository.AccountRepository;
import com.swp.SchoolManagement.request.RegisterRequest;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PersistenceContext
    private EntityManager em;

    @Autowired
    EmailServiceImpl emailService;

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

    public boolean changePassword(long id, String oldPassword, String newPassword) {
        Account a = accountRepository.getById(id);
        if (!bCryptPasswordEncoder.matches(oldPassword, a.getPassword())) {
            return false;
        }
        a.setPassword(bCryptPasswordEncoder.encode(newPassword));
        accountRepository.save(a);

        EmailDetails details = new EmailDetails();
        details.setRecipient(a.getEmail());
        details.setSubject("Password changed");
        details.setMsgBody("Your password was changed at" + new Date().toString());
        emailService.sendSimpleMail(details);
        return true;
    }

    public boolean checkEmailExist(String email) {
        return accountRepository.findByEmail(email) == null;
    }

}
