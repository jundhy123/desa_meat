package com.desameat.website.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class KontakController {

    @GetMapping("/kontak")
    public String kontak(Model model){

        model.addAttribute("title", "Kontak Desa");

        model.addAttribute("alamat",
                "Jl. Desa Meat Balige, Kabupaten Toba, Sumatera Utara");

        model.addAttribute("telepon",
                "0812-3456-7890");

        model.addAttribute("email",
                "desameat@gmail.com");

        return "user/kontak";
    }
}