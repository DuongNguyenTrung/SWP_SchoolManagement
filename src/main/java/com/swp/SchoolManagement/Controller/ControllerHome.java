package com.swp.SchoolManagement.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.logging.Log;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.swp.SchoolManagement.config.auth.JwtTokenProvider;
import com.swp.SchoolManagement.model.Account;
import com.swp.SchoolManagement.model.CustomUserDetails;
import com.swp.SchoolManagement.repository.AccountRepository;
import com.swp.SchoolManagement.request.RegisterRequest;
import com.swp.SchoolManagement.response.TokenRes;
import com.swp.SchoolManagement.services.AccountService;

@RestController
public class ControllerHome {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    private AccountService accountService;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    AccountRepository accountRepository;

    @GetMapping("/register")
    public ResponseEntity<String> register(RegisterRequest request) {
        String a = accountService.register(request);
        return ResponseEntity.ok().body(a);
    }
    @GetMapping("/test")
    public ResponseEntity<String> t() {
        // String a = accountService.register(request);
        return ResponseEntity.ok().body("tét");
    }
    @PostMapping("/login")
    public ResponseEntity<TokenRes> Login(
            @RequestParam("email") String email,
            @RequestParam("password") String password) {
        Account a = accountRepository.findByEmail(email);
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email,
                password);
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        CustomUserDetails details = (CustomUserDetails) authentication.getPrincipal();
        String token = jwtTokenProvider.generateToken(details);
        List<SimpleGrantedAuthority> ls = (List<SimpleGrantedAuthority>) details.getAuthorities().stream()
                .collect(Collectors.toList());
        TokenRes res = new TokenRes(token, details.getUsername(), ls.get(0).getAuthority());
        // res.setToken(jwtTokenProvider);
        return ResponseEntity.ok().body(res);
    }
}
