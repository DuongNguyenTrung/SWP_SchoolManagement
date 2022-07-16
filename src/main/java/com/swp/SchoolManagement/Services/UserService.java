package com.swp.SchoolManagement.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.swp.SchoolManagement.model.Account;
import com.swp.SchoolManagement.model.CustomUserDetails;
import com.swp.SchoolManagement.repository.AccountRepository;

@Service
public class UserService implements UserDetailsService  {
    @Autowired
    AccountRepository accountRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        Account a = accountRepository.findByEmail(username);
        if(a==null){
            throw new UsernameNotFoundException(username);
        }
        return new CustomUserDetails(a);
    }
    
}
