package com.mhp_btn.controllers;

import com.mhp_btn.pojo.ApartmentFloor;
import com.mhp_btn.pojo.ApartmentResident;
import com.mhp_btn.pojo.ApartmentRoom;
import com.mhp_btn.pojo.ApartmentService;
import com.mhp_btn.repositories.FloorRepository;
import com.mhp_btn.services.FloorService;
import com.mhp_btn.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import javax.ws.rs.NotFoundException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/rooms")
@ControllerAdvice
@PropertySource("classpath:configs.properties")
public class RoomController {
    @Autowired
    private RoomService rs;

    @Autowired
    private Environment env;

    @ModelAttribute
    public void commonRoom(Model model) {

        model.addAttribute("rooms", this.rs.getRoomsBlank());
    }

    @GetMapping("/")
    public String index(Model model,@RequestParam Map<String, String> params) {
        int pageSize = Integer.parseInt(env.getProperty("room.pagesize"));

        long totalRoom = this.rs.countRoom();
        int totalPages = (int) Math.ceil((double) totalRoom / pageSize);
        int currentPage = params.get("page") != null ? Integer.parseInt(params.get("page")) : 1;
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("rooms", this.rs.getRoomFilter(params));
        return "rooms";
    }


    @GetMapping("/add")
    public String addRoom(Model model) {
        model.addAttribute("room", new ApartmentRoom());
        return "addRoom";
    }
    @GetMapping("/edit/{id}")
    public String editRoom(@PathVariable int id, Model model) {
        ApartmentRoom room = rs.getRoomById(id);
        model.addAttribute("room", room);
        return "addRoom";
    }


    @PostMapping(path = "/add")
    public String addRoom(@ModelAttribute("room")@Valid ApartmentRoom room, BindingResult rs) {
        System.out.println("Room: " + room.getFloor());


        if (!rs.hasErrors()) {
            try {
                System.out.println("Room: " + room.getFloor());
                room.setCreatedDate(new Date());
                room.setIsBlank((short) 1);
                this.rs.addOrUpdateRoom(room);

                return "redirect:/admin/rooms/?page=1";
            }catch (Exception ex) {
                System.out.println("Lỗi ");
                System.err.println(ex.getMessage());
            }
        }
        return "addRoom";


    }




}