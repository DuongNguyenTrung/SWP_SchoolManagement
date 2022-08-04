package com.swp.SchoolManagement.controller;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.swp.SchoolManagement.DTO.AccountDTO;
import com.swp.SchoolManagement.config.auth.JwtTokenProvider;
import com.swp.SchoolManagement.model.CustomUserDetails;
import com.swp.SchoolManagement.repository.AccountRepository;
import com.swp.SchoolManagement.repository.StudentRepository;
import com.swp.SchoolManagement.repository.TeacherRepository;
import com.swp.SchoolManagement.request.AuthenticationRequest;
import com.swp.SchoolManagement.request.RegisterRequest;
import com.swp.SchoolManagement.response.InfoDTO;
import com.swp.SchoolManagement.response.TokenRes;
import com.swp.SchoolManagement.response.UpdateInfoReq;
import com.swp.SchoolManagement.services.AccountService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

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
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("/register")
    public ResponseEntity<String> register(RegisterRequest request) {
        String a = accountService.register(request);
        return ResponseEntity.ok().body(a);
    }

    @PostMapping("/login")
    public ResponseEntity<TokenRes> Login(@RequestBody AuthenticationRequest authenticationRequest) {

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                authenticationRequest.getEmail(),
                authenticationRequest.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        CustomUserDetails details = (CustomUserDetails) authentication.getPrincipal();
        String token = jwtTokenProvider.generateToken(details);
        List<SimpleGrantedAuthority> ls = (List<SimpleGrantedAuthority>) details.getAuthorities().stream()
                .collect(Collectors.toList());
        String img = "";
        switch (ls.get(0).getAuthority()) {
            case "student":
                img = studentRepository.getById(details.getAccount().getUserId()).getAvatar();
                break;
            case "teacher":
                img = teacherRepository.getById(details.getAccount().getUserId()).getAvatar();
                break;

            default:
                break;
        }

        TokenRes res = new TokenRes(token, details.getUsername(), ls.get(0).getAuthority(),
                details.getAccount().getUserId(), img);
        // res.setToken(jwtTokenProvider);
        return ResponseEntity.ok().body(res);
    }

    @PostMapping("/change-password")
    public ResponseEntity<String> changePassword(String oldPassword, String newPassword) {
        CustomUserDetails user = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();

        if (accountService.changePassword(user.getAccount().getId(), oldPassword, newPassword)) {
            return ResponseEntity.ok().body("change password succesfully !");
        }
        ;
        return ResponseEntity.status(404).body("wrong old password");
    }

    @GetMapping("/getinfo")
    public ResponseEntity<InfoDTO> getInfo() {
        return ResponseEntity.ok().body(accountService.getInfo());
    }

    @PostMapping("/update-info")
    public ResponseEntity<Boolean> updateInfo(UpdateInfoReq updateInfoReq) {
        return ResponseEntity.ok().body(accountService.updateInfo(updateInfoReq));
    }

    @GetMapping("/get-list-account")
    public List<AccountDTO> getListAccount(String email, String role, Integer status) {
        return accountService.getListAccount(email, role, status);
    }

    @PostMapping("/account/update-status")
    public ResponseEntity<Boolean> updateStatus(long id, int status) {
        return ResponseEntity.ok().body(accountService.updateStatusAccount(id, status));
    }

    @GetMapping("/account/refreshtoken")
    public ResponseEntity<Boolean> genToken(String email) {

        return ResponseEntity.ok().body(accountService.genRefreshToken(email));
    }

    @GetMapping("/account/verifytoken")
    public ResponseEntity<Boolean> verifyToken(String email, String token) {
        return ResponseEntity.ok().body(accountService.verifyToken(email, token));
    }

    @PostMapping("/account/resetpassword")
    public ResponseEntity<Boolean> resetPassword(String email, String token, String password) {
        accountService.refreshPassword(email, token, password);
        return ResponseEntity.ok().body(true);
    }
}
