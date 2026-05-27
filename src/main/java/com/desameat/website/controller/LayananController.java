package com.desameat.website.controller;

import com.desameat.website.model.Layanan;
import com.desameat.website.service.LayananService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/layanan")
public class LayananController {

    @Autowired
    private LayananService layananService;

    @GetMapping
    public String showLayananPage(Model model) {
        model.addAttribute("suratTypes", layananService.getAllTypesList());
        return "user/layanan";
    }

    @PostMapping("/submit")
    @ResponseBody
    public Layanan requestSurat(@RequestBody Layanan layanan) {
        return layananService.createRequest(layanan);
    }

    @GetMapping("/lacak")
    public String trackStatus(@RequestParam("query") String query, Model model) {
        List<Layanan> results = layananService.searchByNikOrId(query);
        model.addAttribute("results", results);
        model.addAttribute("tracked", true);
        return "user/layanan";
    }
}
