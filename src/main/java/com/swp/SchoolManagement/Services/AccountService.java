package com.swp.SchoolManagement.Services;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swp.SchoolManagement.DTO.UserDTO;
import com.swp.SchoolManagement.Repository.AccountRepository;
import com.swp.SchoolManagement.util.Util;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;
    @PersistenceContext
    private EntityManager em;

    public List<UserDTO> getUser() {
        String sql = "select Email,fullName from Account";
        List<Object[]> list = em.createNativeQuery(sql).getResultList();
        List<UserDTO> res = list.stream().map(obj -> {
            UserDTO u = new UserDTO();
            u.setEmail(Util.parseString(obj[0]));
            u.setFullname(Util.parseString(obj[1]));
            return u;
        }).collect(Collectors.toList());
        return res;
    }

    public String login(String email, String password) {
        if (email == null || password == null)
            return "";
        String sql = "select role from Account where Email=" + email + " and " + "Password=" + password;
        Object obj = em.createNativeQuery(sql).getSingleResult();
        if (obj == null)
            return "";
        return Util.parseString(obj);
    }
}
