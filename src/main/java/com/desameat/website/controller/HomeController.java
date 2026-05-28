package com.desameat.website.controller;

import com.desameat.website.service.BeritaService;
import com.desameat.website.service.WisataService;
import com.desameat.website.service.UmkmService;
import com.desameat.website.service.PendudukService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    private BeritaService beritaService;

    @Autowired
    private WisataService wisataService;

    @Autowired
    private UmkmService umkmService;

    @Autowired
    private PendudukService pendudukService;

    @GetMapping({"", "/", "/home"})
    public String viewHome(Model model) {
        model.addAttribute("recentBerita", beritaService.getRecentBerita());
        model.addAttribute("destinations", wisataService.getAllWisata());
        
        // Dynamic counts for homepage dashboard
        model.addAttribute("totalPenduduk", pendudukService.getAllPenduduk().size());
        model.addAttribute("totalWisata", wisataService.getAllWisata().size());
        model.addAttribute("totalUmkm", umkmService.getAllUmkm().size());
        
        return "user/home";
    }

    @GetMapping("/profil")
    public String viewProfilePage() {
        return "redirect:/profil-desa";
    }
}
