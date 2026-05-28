package com.desameat.website.controller;

import com.desameat.website.model.Umkm;
import com.desameat.website.service.UmkmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/umkm")
public class UmkmController {

    @Autowired
    private UmkmService umkmService;

    @GetMapping
    public String showUmkmPage(Model model, @RequestParam(value = "kategori", required = false) String kategori) {
        if (kategori != null && !kategori.trim().isEmpty() && !"Semua".equalsIgnoreCase(kategori)) {
            model.addAttribute("products", umkmService.getUmkmByKategori(kategori));
            model.addAttribute("currentKat", kategori);
        } else {
            model.addAttribute("products", umkmService.getAllUmkm());
            model.addAttribute("currentKat", "Semua");
        }
        return "user/umkm";
    }

    @GetMapping("/{id}")
    public String viewDetail(@PathVariable("id") Long id, Model model) {
        Optional<Umkm> item = umkmService.getUmkmById(id);
        if (item.isPresent()) {
            model.addAttribute("item", item.get());
            return "user/umkm-detail";
        }
        return "redirect:/umkm";
    }

    @PostMapping("/admin/add")
    @ResponseBody
    public Umkm createProduct(@RequestBody Umkm item) {
        return umkmService.saveUmkm(item);
    }

    @DeleteMapping("/admin/delete/{id}")
    @ResponseBody
    public String deleteProduct(@PathVariable("id") Long id) {
        try {
            umkmService.deleteUmkm(id);
            return "SUCCESS";
        } catch (Exception e) {
            return "ERROR: " + e.getMessage();
        }
    }
}
