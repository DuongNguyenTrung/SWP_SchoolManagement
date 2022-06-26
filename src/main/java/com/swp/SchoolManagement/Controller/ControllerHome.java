package com.swp.SchoolManagement.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.swp.SchoolManagement.Services.AccountService;

@Controller
public class ControllerHome {
    @Autowired
    private AccountService accountService;

    @GetMapping("/")
    String loginPage() {
        return "index";
    }

    @GetMapping("/test")
    String test(Model model) {
        System.out.println("fullname: " + accountService.getUser().get(1).getFullname());
        model.addAttribute("list", accountService.getUser());
        return "index";
    }
}
