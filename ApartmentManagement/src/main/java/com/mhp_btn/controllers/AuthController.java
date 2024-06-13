package com.mhp_btn.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller

public class AuthController {

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

}
