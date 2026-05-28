package com.desameat.website.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping({"/", "/home"})
    public String home() {
        
        return "user/home";
    }

    @GetMapping("/profil2")
    public String profil() {
        return "user/profil";
    }

    @GetMapping("/berita")
    public String berita() {
        return "user/berita";
    }

<
    @GetMapping("/kontak")
    public String kontak() {
        return "user/kontak";
    }
}
=======
    @GetMapping("/galeri")
    public String galeri() {
        return "user/galeri";
    }

    @GetMapping("/umkm")
    public String umkm() {
        return "user/umkm";
    }


}

