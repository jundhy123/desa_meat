package com.desameat.website.controller;

import com.desameat.website.model.Berita;
import com.desameat.website.service.BeritaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/berita")
public class BeritaController {

    @Autowired
    private BeritaService beritaService;

    @GetMapping
    public String showNewsPage(Model model, @RequestParam(value = "query", required = false) String query) {
        if (query != null && !query.trim().isEmpty()) {
            model.addAttribute("posts", beritaService.searchBerita(query));
        } else {
            model.addAttribute("posts", beritaService.getAllBerita());
        }
        return "user/berita";
    }

    @GetMapping("/{id}")
    public String viewNewsDetail(@PathVariable("id") Long id, Model model) {
        Optional<Berita> post = beritaService.getBeritaById(id);
        if (post.isPresent()) {
            model.addAttribute("post", post.get());
            model.addAttribute("recentPosts", beritaService.getRecentBerita());
            return "user/berita-detail";
        }
        return "redirect:/berita";
    }

    @PostMapping("/admin/add")
    @ResponseBody
    public Berita createPost(@RequestBody Berita berita) {
        return beritaService.saveBerita(berita);
    }

    @DeleteMapping("/admin/delete/{id}")
    @ResponseBody
    public String deletePost(@PathVariable("id") Long id) {
        try {
            beritaService.deleteBerita(id);
            return "SUCCESS";
        } catch (Exception e) {
            return "ERROR: " + e.getMessage();
        }
    }
}
