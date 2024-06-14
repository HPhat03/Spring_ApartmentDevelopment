package com.mhp_btn.controllers;

import com.mhp_btn.pojo.ApartmentRoom;
import com.mhp_btn.pojo.ApartmentUser;
import com.mhp_btn.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService us;

    @GetMapping("/")
    public String index(Model model, @RequestParam Map<String, String> params) {
       model.addAttribute("users", this.us.getUsers(params));
       return "users";
    }
    @GetMapping("/{id}")
    public String detailUser(Model model, @PathVariable int id) {
        model.addAttribute("user", this.us.getUserByID(id));
        return "user";
    }
    @GetMapping("/add")
    public String addUser(Model model) {
        return "addUser";
    }
    @GetMapping("/edit/{id}")
    public String editUser(Model model, @PathVariable int id) {
        model.addAttribute("user", this.us.getUserByID(id));
        return "editUser";
    }
    @PatchMapping ("/edit/{id}")
    public String edit(@ModelAttribute("user") ApartmentUser u, @PathVariable int id) {
        //    chưa bắt lỗi nhập liệu
//        this.us.ChangeOrInitialize()

        return "redirect:/users/";
    }



}
