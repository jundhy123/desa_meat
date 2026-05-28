package com.desameat.website.controller;

import com.desameat.website.model.Umkm;
import com.desameat.website.service.UmkmService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/umkm")
public class UmkmController {

    private final UmkmService umkmService;

    public UmkmController(UmkmService umkmService) {
        this.umkmService = umkmService;
    }

    @GetMapping
    public String umkmPage(Model model) {

        model.addAttribute("listUMKM", umkmService.getAllUMKM());

        model.addAttribute("umkm", new Umkm());

        return "user/umkm";
    }

    @PostMapping("/save")
    public String saveUMKM(@ModelAttribute Umkm umkm) {

        umkmService.saveUMKM(umkm);

        return "redirect:/umkm";
    }
}