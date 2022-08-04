package com.swp.SchoolManagement.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.swp.SchoolManagement.DTO.AccountDTO;
import com.swp.SchoolManagement.DTO.EmailDetails;
import com.swp.SchoolManagement.config.exception.GlobalExeption;
import com.swp.SchoolManagement.model.Account;
import com.swp.SchoolManagement.model.CustomUserDetails;
import com.swp.SchoolManagement.model.Student;
import com.swp.SchoolManagement.model.Teacher;
import com.swp.SchoolManagement.repository.AccountRepository;
import com.swp.SchoolManagement.repository.StudentRepository;
import com.swp.SchoolManagement.repository.TeacherRepository;
import com.swp.SchoolManagement.request.RegisterRequest;
import com.swp.SchoolManagement.response.InfoDTO;
import com.swp.SchoolManagement.response.UpdateInfoReq;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PersistenceContext
    private EntityManager em;

    @Autowired
    EmailServiceImpl emailService;

    public String register(RegisterRequest request) {
        if (accountRepository.findByEmail(request.email) != null) {
            throw new GlobalExeption(404, "Email exist!");
        }
        Long userId = null;
        switch (request.role) {
            case "student":
                Student s = new Student();
                String scode = studentRepository.findTopByOrderByStudentIdDesc().getStudentCode();
                s.setStudentCode("SE" + (Integer.parseInt(scode.substring(2)) + 1));
                s.setFullname(request.fullname);
                userId = studentRepository.save(s).getStudentId();
                break;
            case "teacher":
                Teacher t = new Teacher();
                t.setFullname(request.fullname);
                userId = teacherRepository.save(t).getTeacherId();
                break;

            default:
                break;
        }
        Account a = new Account();
        a.setEmail(request.email);
        a.setPassword(bCryptPasswordEncoder.encode(request.password));
        a.setRole(request.role);
        a.setUserId(userId);
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

    public InfoDTO getInfo() {
        CustomUserDetails details = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        switch (details.getAccount().getRole()) {
            case "student":
                Student s = studentRepository.getById(details.getAccount().getUserId());
                InfoDTO i = new InfoDTO();
                i.setId(details.getAccount().getUserId());
                i.setEmail(details.getUsername());
                i.setAddress(s.getAddress());
                i.setFullname(s.getFullname());
                i.setDob(s.getDob());
                i.setGender(s.getGender());
                i.setPhone(s.getPhone());
                i.setAvatar(s.getAvatar());
                return i;
            case "teacher":
                Teacher t = teacherRepository.getById(details.getAccount().getUserId());
                i = new InfoDTO();
                i.setId(details.getAccount().getUserId());
                i.setEmail(details.getUsername());
                i.setAddress(t.getAddress());
                i.setFullname(t.getFullname());
                i.setDob(t.getDob());
                i.setGender(t.getGender());
                i.setPhone(t.getPhone());
                i.setAvatar(t.getAvatar());
                return i;

            default:
                break;
        }
        return null;
    }

    public boolean updateInfo(UpdateInfoReq request) {
        CustomUserDetails details = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        switch (details.getAccount().getRole()) {
            case "student":
                Student s = studentRepository.getById(details.getAccount().getUserId());
                s.setAddress(request.getAddress());
                s.setFullname(request.getFullname());
                s.setDob(request.getDob());
                s.setGender(request.isGender());
                s.setPhone(request.getPhone());
                s.setAvatar(request.getAvatar());
                studentRepository.save(s);
                return true;
            case "teacher":
                Teacher t = teacherRepository.getById(details.getAccount().getUserId());
                t.setAddress(request.getAddress());
                t.setFullname(request.getFullname());
                t.setDob(request.getDob());
                t.setGender(request.isGender());
                t.setPhone(request.getPhone());
                t.setAvatar(request.getAvatar());
                teacherRepository.save(t);
                return true;

            default:
                break;
        }
        return false;
    }

    public List<AccountDTO> getListAccount(String email, String role, Integer status) {

        return (List<AccountDTO>) accountRepository.findAll(accountSpecification(email, role, status)).stream()
                .map(e -> {
                    String fullname = "";
                    switch (e.getRole()) {
                        case "student":
                            fullname = studentRepository.getById(e.getUserId()).getFullname();
                            break;
                        case "teacher":
                            fullname = teacherRepository.getById(e.getUserId()).getFullname();
                            break;
                    }
                    AccountDTO a = new AccountDTO(e.getId(), e.getEmail(), fullname, e.getRole(), e.getStatus());
                    return a;
                }).collect(Collectors.toList());
    }

    private Specification<Account> accountSpecification(String email, String role, Integer status) {
        return new Specification<Account>() {

            @Override
            public Predicate toPredicate(Root<Account> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
                List<Predicate> predicates = new ArrayList<>();
                if (email != null) {
                    predicates.add(builder.like(root.get("email"), "%" + email + "%"));
                }
                if (role != null) {
                    predicates.add(builder.equal(root.get("role"), role));
                }
                if (status != null) {
                    predicates.add(builder.equal(root.get("status"), status));
                }
                return builder.and(predicates.toArray(new Predicate[0]));
            }

        };

    }

    public boolean updateStatusAccount(long id, int status) {
        Account a = accountRepository.getById(id);
        a.setStatus(status);
        accountRepository.save(a);
        return true;
    }

    public boolean genRefreshToken(String email) {
        Random r = new Random();
        int number = r.nextInt(999999);
        Account a = accountRepository.findByEmail(email);
        if (a == null)
            throw new GlobalExeption(400, "Email " + email + " not found");
        a.setRefreshToken(String.format("%06d", number));
        accountRepository.save(a);
        emailService
                .sendSimpleMail(new EmailDetails(email, "Your refresh token is: " + String.format("%06d", number),
                        "Token reset password", null));
        return true;
    }

    public String refreshPassword(String email, String token, String password) {
        Account a = accountRepository.findByEmail(email);
        System.out.println(a.getRefreshToken() + " " + token);
        if (a.getRefreshToken().isEmpty() || !a.getRefreshToken().equals(token))
            throw new GlobalExeption(403, "Token does not match");
        a.setPassword(bCryptPasswordEncoder.encode(password));

        a.setRefreshToken("");
        accountRepository.save(a);
        return "Password reset successfully!";
    }

    public boolean verifyToken(String email, String token) {
        Account a = accountRepository.findByEmail(email);
        System.out.println(a.getRefreshToken() + " " + token);
        if (a.getRefreshToken().isEmpty() || !a.getRefreshToken().equals(token))
            return false;
        return true;
    }
}
