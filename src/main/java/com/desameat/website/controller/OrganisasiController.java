package com.desameat.website.controller;

import com.desameat.website.model.Organisasi;
import com.desameat.website.service.OrganisasiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/organisasi")
public class OrganisasiController {

    @Autowired
    private OrganisasiService organisasiService;

    @GetMapping
    public String showOrganizations(Model model) {

        List<Organisasi> data = organisasiService.getAllOrganisasi();

        model.addAttribute("orgs", data);

        return "user/organisasi";
    }
}