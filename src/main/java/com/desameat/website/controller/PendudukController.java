package com.desameat.website.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.desameat.website.service.PendudukService;

@Controller
public class PendudukController {

    @Autowired
    private PendudukService pendudukService;

    // Untuk halaman publik/user biasa
    @GetMapping("/penduduk")
    public String viewPendudukStatistik(Model model) {
        // 1. Ambil map statistik terpusat dari service
        Map<String, Object> statistik = pendudukService.getStatistik();
        
        // 2. Pecah dan masukkan ke Model agar nama variabel di Thymeleaf user tetap sama
        model.addAttribute("totalPenduduk", statistik.get("total"));
        model.addAttribute("statGender", statistik.get("gender"));
        model.addAttribute("statPekerjaan", statistik.get("pekerjaan"));
        
        return "user/penduduk"; // Mengarah ke templates/user/penduduk.html
    }
}