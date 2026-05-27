package com.desameat.website.controller;

import com.desameat.website.dto.DashboardStatsDTO;
import com.desameat.website.model.Berita;
import com.desameat.website.model.Berita.KategoriBerita;
import com.desameat.website.model.Berita.StatusBerita;
import com.desameat.website.model.Pengumuman;
import com.desameat.website.model.Pengumuman.PrioritasPengumuman;
import com.desameat.website.model.Pengumuman.StatusPengumuman;
import com.desameat.website.service.BeritaService;
import com.desameat.website.service.PengumumanService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private BeritaService beritaService;

    @Autowired
    private PengumumanService pengumumanService;

    // ============================
    // DASHBOARD
    // ============================
    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        DashboardStatsDTO stats = new DashboardStatsDTO();
        stats.setTotalBerita(beritaService.hitungTotal());
        stats.setBeritaPublished(beritaService.hitungByStatus(StatusBerita.PUBLISHED));
        stats.setBeritaDraft(beritaService.hitungByStatus(StatusBerita.DRAFT));
        stats.setTotalPengumuman(pengumumanService.hitungTotal());
        stats.setPengumumanAktif(pengumumanService.hitungByStatus(StatusPengumuman.AKTIF));
        stats.setPengumumanNonaktif(pengumumanService.hitungByStatus(StatusPengumuman.NONAKTIF));
        stats.setBeritaKategoriBerita(beritaService.hitungByKategori(KategoriBerita.BERITA));
        stats.setBeritaKategoriPengumuman(beritaService.hitungByKategori(KategoriBerita.PENGUMUMAN));
        stats.setBeritaKategoriAcara(beritaService.hitungByKategori(KategoriBerita.ACARA));
        stats.setBeritaKategoriInformasi(beritaService.hitungByKategori(KategoriBerita.INFORMASI));

        model.addAttribute("stats", stats);
        model.addAttribute("beritaTerbaru", beritaService.beritaTerbaru());
        model.addAttribute("pengumumanTerbaru", pengumumanService.pengumumanTerbaru());
        model.addAttribute("activePage", "dashboard");
        return "admin/dashboard";
    }

    // ============================
    // BERITA - LIST
    // ============================
    @GetMapping("/berita")
    public String daftarBerita(
            @RequestParam(defaultValue = "") String keyword,
            @RequestParam(defaultValue = "") String kategori,
            @RequestParam(defaultValue = "") String status,
            @RequestParam(defaultValue = "0") int page,
            Model model) {

        Page<Berita> beritaPage = beritaService.cariDenganFilter(keyword, kategori, status, page, 10);
        model.addAttribute("beritaList", beritaPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", beritaPage.getTotalPages());
        model.addAttribute("totalItems", beritaPage.getTotalElements());
        model.addAttribute("keyword", keyword);
        model.addAttribute("kategori", kategori);
        model.addAttribute("status", status);
        model.addAttribute("kategoriList", KategoriBerita.values());
        model.addAttribute("statusList", StatusBerita.values());
        model.addAttribute("activePage", "berita");
        return "admin/berita/daftar";
    }

    // BERITA - FORM TAMBAH
    @GetMapping("/berita/tambah")
    public String formTambahBerita(Model model) {
        model.addAttribute("berita", new Berita());
        model.addAttribute("kategoriList", KategoriBerita.values());
        model.addAttribute("statusList", StatusBerita.values());
        model.addAttribute("isEdit", false);
        model.addAttribute("activePage", "berita");
        return "admin/berita/form";
    }

    // BERITA - SIMPAN
    @PostMapping("/berita/tambah")
    public String simpanBerita(
            @Valid @ModelAttribute("berita") Berita berita,
            BindingResult result,
            @RequestParam(value = "fileGambar", required = false) MultipartFile fileGambar,
            Model model,
            RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            model.addAttribute("kategoriList", KategoriBerita.values());
            model.addAttribute("statusList", StatusBerita.values());
            model.addAttribute("isEdit", false);
            return "admin/berita/form";
        }
        try {
            beritaService.simpan(berita, fileGambar);
            redirectAttributes.addFlashAttribute("successMsg", "Berita berhasil ditambahkan!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMsg", "Gagal: " + e.getMessage());
        }
        return "redirect:/admin/berita";
    }

    // BERITA - FORM EDIT
    @GetMapping("/berita/edit/{id}")
    public String formEditBerita(@PathVariable Long id, Model model, RedirectAttributes ra) {
        return beritaService.cariById(id).map(berita -> {
            model.addAttribute("berita", berita);
            model.addAttribute("kategoriList", KategoriBerita.values());
            model.addAttribute("statusList", StatusBerita.values());
            model.addAttribute("isEdit", true);
            model.addAttribute("activePage", "berita");
            return "admin/berita/form";
        }).orElseGet(() -> {
            ra.addFlashAttribute("errorMsg", "Berita tidak ditemukan!");
            return "redirect:/admin/berita";
        });
    }

    // BERITA - UPDATE
    @PostMapping("/berita/edit/{id}")
    public String updateBerita(
            @PathVariable Long id,
            @Valid @ModelAttribute("berita") Berita berita,
            BindingResult result,
            @RequestParam(value = "fileGambar", required = false) MultipartFile fileGambar,
            Model model,
            RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            model.addAttribute("kategoriList", KategoriBerita.values());
            model.addAttribute("statusList", StatusBerita.values());
            model.addAttribute("isEdit", true);
            return "admin/berita/form";
        }
        try {
            beritaService.update(id, berita, fileGambar);
            redirectAttributes.addFlashAttribute("successMsg", "Berita berhasil diupdate!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMsg", "Gagal: " + e.getMessage());
        }
        return "redirect:/admin/berita";
    }

    // BERITA - DETAIL
    @GetMapping("/berita/detail/{id}")
    public String detailBerita(@PathVariable Long id, Model model, RedirectAttributes ra) {
        return beritaService.cariById(id).map(berita -> {
            model.addAttribute("berita", berita);
            model.addAttribute("activePage", "berita");
            return "admin/berita/detail";
        }).orElseGet(() -> {
            ra.addFlashAttribute("errorMsg", "Berita tidak ditemukan!");
            return "redirect:/admin/berita";
        });
    }

    // BERITA - HAPUS
    @PostMapping("/berita/hapus/{id}")
    public String hapusBerita(@PathVariable Long id, RedirectAttributes ra) {
        try {
            beritaService.hapus(id);
            ra.addFlashAttribute("successMsg", "Berita berhasil dihapus!");
        } catch (Exception e) {
            ra.addFlashAttribute("errorMsg", "Gagal hapus: " + e.getMessage());
        }
        return "redirect:/admin/berita";
    }

    // BERITA - UBAH STATUS
    @PostMapping("/berita/status/{id}")
    public String ubahStatusBerita(@PathVariable Long id, @RequestParam StatusBerita status, RedirectAttributes ra) {
        try {
            beritaService.ubahStatus(id, status);
            ra.addFlashAttribute("successMsg", "Status berita diubah!");
        } catch (Exception e) {
            ra.addFlashAttribute("errorMsg", "Gagal: " + e.getMessage());
        }
        return "redirect:/admin/berita";
    }

    // ============================
    // PENGUMUMAN - LIST
    // ============================
    @GetMapping("/pengumuman")
    public String daftarPengumuman(
            @RequestParam(defaultValue = "") String keyword,
            @RequestParam(defaultValue = "") String status,
            @RequestParam(defaultValue = "") String prioritas,
            @RequestParam(defaultValue = "0") int page,
            Model model) {

        Page<Pengumuman> pengumumanPage = pengumumanService.cariDenganFilter(keyword, status, prioritas, page, 10);
        model.addAttribute("pengumumanList", pengumumanPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", pengumumanPage.getTotalPages());
        model.addAttribute("totalItems", pengumumanPage.getTotalElements());
        model.addAttribute("keyword", keyword);
        model.addAttribute("status", status);
        model.addAttribute("prioritas", prioritas);
        model.addAttribute("statusList", StatusPengumuman.values());
        model.addAttribute("prioritasList", PrioritasPengumuman.values());
        model.addAttribute("activePage", "pengumuman");
        return "admin/pengumuman/daftar";
    }

    // PENGUMUMAN - FORM TAMBAH
    @GetMapping("/pengumuman/tambah")
    public String formTambahPengumuman(Model model) {
        model.addAttribute("pengumuman", new Pengumuman());
        model.addAttribute("statusList", StatusPengumuman.values());
        model.addAttribute("prioritasList", PrioritasPengumuman.values());
        model.addAttribute("isEdit", false);
        model.addAttribute("activePage", "pengumuman");
        return "admin/pengumuman/form";
    }

    // PENGUMUMAN - SIMPAN
    @PostMapping("/pengumuman/tambah")
    public String simpanPengumuman(
            @Valid @ModelAttribute("pengumuman") Pengumuman pengumuman,
            BindingResult result,
            Model model,
            RedirectAttributes ra) {

        if (result.hasErrors()) {
            model.addAttribute("statusList", StatusPengumuman.values());
            model.addAttribute("prioritasList", PrioritasPengumuman.values());
            model.addAttribute("isEdit", false);
            return "admin/pengumuman/form";
        }
        try {
            pengumumanService.simpan(pengumuman);
            ra.addFlashAttribute("successMsg", "Pengumuman berhasil ditambahkan!");
        } catch (Exception e) {
            ra.addFlashAttribute("errorMsg", "Gagal: " + e.getMessage());
        }
        return "redirect:/admin/pengumuman";
    }

    // PENGUMUMAN - FORM EDIT
    @GetMapping("/pengumuman/edit/{id}")
    public String formEditPengumuman(@PathVariable Long id, Model model, RedirectAttributes ra) {
        return pengumumanService.cariById(id).map(p -> {
            model.addAttribute("pengumuman", p);
            model.addAttribute("statusList", StatusPengumuman.values());
            model.addAttribute("prioritasList", PrioritasPengumuman.values());
            model.addAttribute("isEdit", true);
            model.addAttribute("activePage", "pengumuman");
            return "admin/pengumuman/form";
        }).orElseGet(() -> {
            ra.addFlashAttribute("errorMsg", "Pengumuman tidak ditemukan!");
            return "redirect:/admin/pengumuman";
        });
    }

    // PENGUMUMAN - UPDATE
    @PostMapping("/pengumuman/edit/{id}")
    public String updatePengumuman(
            @PathVariable Long id,
            @Valid @ModelAttribute("pengumuman") Pengumuman pengumuman,
            BindingResult result,
            Model model,
            RedirectAttributes ra) {

        if (result.hasErrors()) {
            model.addAttribute("statusList", StatusPengumuman.values());
            model.addAttribute("prioritasList", PrioritasPengumuman.values());
            model.addAttribute("isEdit", true);
            return "admin/pengumuman/form";
        }
        try {
            pengumumanService.update(id, pengumuman);
            ra.addFlashAttribute("successMsg", "Pengumuman berhasil diupdate!");
        } catch (Exception e) {
            ra.addFlashAttribute("errorMsg", "Gagal: " + e.getMessage());
        }
        return "redirect:/admin/pengumuman";
    }

    // PENGUMUMAN - DETAIL
    @GetMapping("/pengumuman/detail/{id}")
    public String detailPengumuman(@PathVariable Long id, Model model, RedirectAttributes ra) {
        return pengumumanService.cariById(id).map(p -> {
            model.addAttribute("pengumuman", p);
            model.addAttribute("activePage", "pengumuman");
            return "admin/pengumuman/detail";
        }).orElseGet(() -> {
            ra.addFlashAttribute("errorMsg", "Pengumuman tidak ditemukan!");
            return "redirect:/admin/pengumuman";
        });
    }

    // PENGUMUMAN - HAPUS
    @PostMapping("/pengumuman/hapus/{id}")
    public String hapusPengumuman(@PathVariable Long id, RedirectAttributes ra) {
        try {
            pengumumanService.hapus(id);
            ra.addFlashAttribute("successMsg", "Pengumuman berhasil dihapus!");
        } catch (Exception e) {
            ra.addFlashAttribute("errorMsg", "Gagal hapus: " + e.getMessage());
        }
        return "redirect:/admin/pengumuman";
    }

    // PENGUMUMAN - UBAH STATUS
    @PostMapping("/pengumuman/status/{id}")
    public String ubahStatusPengumuman(@PathVariable Long id, @RequestParam StatusPengumuman status, RedirectAttributes ra) {
        try {
            pengumumanService.ubahStatus(id, status);
            ra.addFlashAttribute("successMsg", "Status pengumuman diubah!");
        } catch (Exception e) {
            ra.addFlashAttribute("errorMsg", "Gagal: " + e.getMessage());
        }
        return "redirect:/admin/pengumuman";
    }
}
