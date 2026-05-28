package com.desameat.website.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class KontakController {

    @GetMapping("/kontak")
    public String showContactPage() {
        return "user/kontak";
    }

    @PostMapping("/kontak/send")
    public String sendMessage(
            @RequestParam("name") String name,
            @RequestParam("email") String email,
            @RequestParam("subject") String subject,
            @RequestParam("message") String message,
            Model model) {
        
        model.addAttribute("success", "Terima kasih, " + name + ". Pesan Anda telah terkirim ke Kantor Desa Meat.");
        return "user/kontak";
    }
}
