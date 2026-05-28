package com.desameat.website.controller;

import com.desameat.website.model.Galeri;
import com.desameat.website.service.GaleriService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/galeri")
public class GaleriController {

    @Autowired
    private GaleriService galeriService;

    @GetMapping
    public String showGallery(Model model) {
        model.addAttribute("listGaleri", galeriService.getAllGaleri());
        return "user/galeri";
    }

    @PostMapping("/admin/add")
    @ResponseBody
    public Galeri uploadPhoto(@RequestBody Galeri galeri) {
        return galeriService.saveGaleri(galeri);
    }

    @DeleteMapping("/admin/delete/{id}")
    @ResponseBody
    public String removePhoto(@PathVariable("id") Long id) {
        try {
            galeriService.deleteGaleri(id);
            return "SUCCESS";
        } catch (Exception e) {
            return "ERROR: " + e.getMessage();
        }
    }
}
