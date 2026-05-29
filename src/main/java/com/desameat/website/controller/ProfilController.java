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

    @GetMapping
    public String showProfilePage() {
        return "user/profil";
    }
}
