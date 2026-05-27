package com.desameat.website.controller;

import com.desameat.website.model.Wisata;
import com.desameat.website.service.WisataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/wisata")
public class WisataController {

    @Autowired
    private WisataService wisataService;

    @GetMapping
    public String showTourismPage(Model model) {
        model.addAttribute("destinations", wisataService.getAllWisata());
        return "user/wisata";
    }

    @GetMapping("/{id}")
    public String viewDestinationDetail(@PathVariable("id") Long id, Model model) {
        Optional<Wisata> opt = wisataService.getWisataById(id);
        if (opt.isPresent()) {
            model.addAttribute("destination", opt.get());
            return "user/wisata-detail";
        }
        return "redirect:/wisata";
    }

    @PostMapping("/admin/add")
    @ResponseBody
    public Wisata createDestination(@RequestBody Wisata wisata) {
        return wisataService.saveWisata(wisata);
    }

    @DeleteMapping("/admin/delete/{id}")
    @ResponseBody
    public String deleteDestination(@PathVariable("id") Long id) {
        try {
            wisataService.deleteWisata(id);
            return "SUCCESS";
        } catch (Exception e) {
            return "ERROR: " + e.getMessage();
        }
    }
}
