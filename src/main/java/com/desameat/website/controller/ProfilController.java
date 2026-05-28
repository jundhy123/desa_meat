package com.desameat.website.controller;

import com.desameat.website.model.ProfilDesa;
import com.desameat.website.service.ProfilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/profil-desa")
public class ProfilController {

    @Autowired
    private ProfilService profilService;

    @GetMapping
    public String showProfilePage(Model model) {
        Optional<ProfilDesa> opt = profilService.getProfil();
        if (opt.isPresent()) {
            model.addAttribute("profil", opt.get());
        } else {
            // Default mock fallbacks if DB empty
            ProfilDesa fallback = new ProfilDesa();
            fallback.setSejarah("Desa Meat terletak di pesisir Danau Toba, Kecamatan Tampahan, Kabupaten Toba Samosir. Terkenal dengan keindahan alam, budaya tenun ulos tradisional, serta keramahtamahan warganya.");
            fallback.setVisi("Mewujudkan Desa Meat sebagai pusat agrowisata budaya dan digital mandiri unggul tahun 2029.");
            fallback.setMisi("1. Mengembangkan pariwisata berbasis pelestarian budaya Toba.\n2. Mengoptimalkan pelayanan publik terintegrasi digital.\n3. Meningkatkan ekonomi kreatif tenun Ulos.");
            fallback.setKepalaDesa("Janri Simanjuntak");
            fallback.setDeskripsiGeografis("Meat dikelilingi perbukitan hijau elok dan menghadap langsung ke hamparan perairan Danau Toba.");
            model.addAttribute("profil", fallback);
        }
        return "user/profil";
    }

    @PostMapping("/admin/save")
    @ResponseBody
    public ProfilDesa updateProfile(@RequestBody ProfilDesa profilDesa) {
        return profilService.saveProfil(profilDesa);
    }
}
