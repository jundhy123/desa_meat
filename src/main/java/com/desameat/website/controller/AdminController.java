package com.desameat.website.controller;

import com.desameat.website.service.LayananService;
import com.desameat.website.service.WisataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private LayananService layananService;

    @Autowired
    private WisataService wisataService;

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("layananRequests", layananService.getAllRequests());
        model.addAttribute("destinations", wisataService.getAllWisata());
        return "admin/dashboard";
    }
}
