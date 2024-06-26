package com.mhp_btn.controllers;

import com.mhp_btn.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

public class AuthController {
    @Autowired
    private UserService us;

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }


}
