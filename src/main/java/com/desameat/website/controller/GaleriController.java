package com.desameat.website.controller;

import com.desameat.website.model.Galeri;
import com.desameat.website.service.GaleriService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/galeri")
public class GaleriController {

    private final GaleriService galeriService;

    public GaleriController(GaleriService galeriService) {
        this.galeriService = galeriService;
    }

    @GetMapping
    public String galeriPage(Model model) {

        model.addAttribute("listGaleri", galeriService.getAllGaleri());

        model.addAttribute("galeri", new Galeri());

        return "user/galeri";
    }

    @PostMapping("/save")
    public String saveGaleri(@ModelAttribute Galeri galeri) {

        galeriService.saveGaleri(galeri);

        return "redirect:/galeri";
    }
}