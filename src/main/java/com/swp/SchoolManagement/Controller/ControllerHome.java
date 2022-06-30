package com.swp.SchoolManagement.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.swp.SchoolManagement.Services.AccountService;

@Controller
public class ControllerHome {
    @Autowired
    private AccountService accountService;

    @GetMapping("/")
    String loginPage() {
        return "Login";
    }

    @PostMapping("/login")
    String test(Model model,
            @RequestParam("email") String email,
            @RequestParam("password") String password) {
        System.out.println(email + " " + password);
        String url = accountService.login(email, password);
        if (url.isEmpty()) {
            model.addAttribute("fail", "Sai tên đăng nhập hoặc mật khẩu !");
            return "Login";
        }
        return url;
    }
}
