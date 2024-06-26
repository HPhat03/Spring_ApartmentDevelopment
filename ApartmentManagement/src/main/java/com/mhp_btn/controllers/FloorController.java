package com.mhp_btn.controllers;

import com.mhp_btn.services.FloorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
@ControllerAdvice
public class FloorController {

        @Autowired
        private FloorService floorService;
        @ModelAttribute
        public void commonAttr(Model model) {
            model.addAttribute("floors", this.floorService.getAllFloor());
        }


}
