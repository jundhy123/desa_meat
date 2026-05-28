package com.desameat.website.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.desameat.website.model.Penduduk;
import com.desameat.website.service.PendudukService;

@Controller
public class PendudukController {

    @Autowired
    private PendudukService pendudukService;

    // ─── ADMIN ───────────────────────────────────────────────

    @GetMapping("/admin/penduduk")
    public String adminPenduduk(Model model,
                                @RequestParam(required = false) String cari) {
        if (cari != null && !cari.isBlank()) {
            model.addAttribute("pendudukList", pendudukService.cariByNama(cari));
            model.addAttribute("cari", cari);
        } else {
            model.addAttribute("pendudukList", pendudukService.getAllPenduduk());
        }
        model.addAttribute("statistik", pendudukService.getStatistik());
        model.addAttribute("pendudukBaru", new Penduduk());
        return "admin/penduduk";
    }

    @PostMapping("/admin/penduduk/simpan")
    public String simpan(@ModelAttribute Penduduk penduduk,
                         RedirectAttributes redirect) {
        // Cek NIK duplikat hanya saat tambah baru
        if (penduduk.getId() == null && pendudukService.nikSudahAda(penduduk.getNik())) {
            redirect.addFlashAttribute("error", "NIK " + penduduk.getNik() + " sudah terdaftar!");
            return "redirect:/admin/penduduk";
        }
        pendudukService.simpan(penduduk);
        redirect.addFlashAttribute("sukses", penduduk.getId() == null
                ? "Data penduduk berhasil ditambahkan."
                : "Data penduduk berhasil diperbarui.");
        return "redirect:/admin/penduduk";
    }

    @GetMapping("/admin/penduduk/edit/{id}")
    public String formEdit(@PathVariable Long id, Model model) {
        Optional<Penduduk> opt = pendudukService.getById(id);
        if (opt.isEmpty()) return "redirect:/admin/penduduk";

        model.addAttribute("pendudukEdit", opt.get());
        model.addAttribute("pendudukList", pendudukService.getAllPenduduk());
        model.addAttribute("statistik", pendudukService.getStatistik());
        model.addAttribute("pendudukBaru", new Penduduk());
        return "admin/penduduk";
    }

    @PostMapping("/admin/penduduk/hapus/{id}")
    public String hapus(@PathVariable Long id, RedirectAttributes redirect) {
        pendudukService.hapus(id);
        redirect.addFlashAttribute("sukses", "Data penduduk berhasil dihapus.");
        return "redirect:/admin/penduduk";
    }

}