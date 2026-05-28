package com.desameat.website.controller;

import com.desameat.website.model.*;
import com.desameat.website.service.ProfilService;
import com.desameat.website.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProfilController {

    @Autowired
    private ProfilService profilService;

    @Autowired
    private UserRepository userRepository;

    // ================= USER VIEW =================
    @GetMapping("/profil")
    public String profilUser(Model model) {

        User admin = userRepository.findByUsername("admin").orElse(null);

        ProfilDesa profil = null;
        if (admin != null) {
            profil = admin.getProfilDesa();
        if (profil != null) {
            profil.getVisi().sort((a, b) -> a.getUrutan().compareTo(b.getUrutan()));
            profil.getMisi().sort((a, b) -> a.getUrutan().compareTo(b.getUrutan()));
        }
        }

        model.addAttribute("profil", profil);
        model.addAttribute("activePage", "profil");

        return "user/profil";
    }

    // ================= ADMIN VIEW =================
    @GetMapping("/admin/profil")
    public String adminProfil(Model model) {

        model.addAttribute("listVisi", profilService.getAllVisi());
        model.addAttribute("listMisi", profilService.getAllMisi());
        model.addAttribute("listProfil", profilService.getAllProfil());

        model.addAttribute("activePage", "profil");

        return "admin/profil/index";
    }

    // ================= AMBIL PROFIL DESA =================
    private ProfilDesa getCurrentProfilDesa() {
        User admin = userRepository.findByUsername("admin").orElse(null);

        if (admin != null) {
            return admin.getProfilDesa();
        }

        throw new RuntimeException("Profil Desa tidak ditemukan untuk admin");
    }

    // ================= VISI CRUD =================

    @PostMapping("/admin/visi/save")
    public String saveVisi(@ModelAttribute Visi visi) {

        // 🔥 WAJIB SET FK supaya tidak NULL
        visi.setProfilDesa(getCurrentProfilDesa());

        profilService.saveVisi(visi);

        return "redirect:/admin/profil";
    }

    @GetMapping("/admin/visi/delete/{id}")
    public String deleteVisi(@PathVariable Long id) {
        profilService.deleteVisi(id);
        return "redirect:/admin/profil";
    }

    // ================= MISI CRUD =================

    @PostMapping("/admin/misi/save")
    public String saveMisi(@ModelAttribute Misi misi) {

        // 🔥 WAJIB SET FK supaya tidak NULL
        misi.setProfilDesa(getCurrentProfilDesa());

        profilService.saveMisi(misi);

        return "redirect:/admin/profil";
    }

    @GetMapping("/admin/misi/delete/{id}")
    public String deleteMisi(@PathVariable Long id) {
        profilService.deleteMisi(id);
        return "redirect:/admin/profil";
    }
}