package com.desameat.website.controller;

import com.desameat.website.model.*;
import com.desameat.website.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.desameat.website.utils.FileUploadUtil;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.Collections;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private PendudukService pendudukService;

    @Autowired
    private LayananService layananService;

    @Autowired
    private WisataService wisataService;

    @Autowired
    private UmkmService umkmService;

    @Autowired
    private BeritaService beritaService;

    @Autowired
    private OrganisasiService organisasiService;

    @Autowired
    private GaleriService galeriService;

    @Autowired
    private ProfilService profilService;

    // ==========================================
    // DASHBOARD
    // ==========================================
    @GetMapping({"", "/dashboard"})
    public String viewDashboard(Model model) {

        model.addAttribute("cntPenduduk", pendudukService.getAllPenduduk().size());
        model.addAttribute("cntWisata", wisataService.getAllWisata().size());
        model.addAttribute("cntUmkm", umkmService.getAllUmkm().size());
        model.addAttribute("cntBerita", beritaService.getAllBerita().size());

        model.addAttribute("cntLayananPending",
                layananService.getAllRequests()
                        .stream()
                        .filter(r -> r.getStatus() != null && "PENDING".equalsIgnoreCase(r.getStatus().name()))
                        .count()
        );

        model.addAttribute("recentLayanan", layananService.getAllRequests());

        return "admin/dashboard";
    }

    // ==========================================
    // BERITA
    // ==========================================
    @GetMapping("/berita")
    public String adminBerita(Model model) {
        model.addAttribute("posts",
                beritaService.getAllBerita() != null
                        ? beritaService.getAllBerita()
                        : java.util.Collections.emptyList()
        );
        return "admin/berita/index";
    }

    @GetMapping("/berita/create")
    public String createBeritaForm(Model model) {
        model.addAttribute("post", new Berita());
        return "admin/berita/create";
    }

    @GetMapping("/berita/edit/{id}")
    public String editBeritaForm(@PathVariable Long id, Model model) {

        Optional<Berita> post = beritaService.getBeritaById(id);

        if (post.isPresent()) {
            model.addAttribute("post", post.get());
            return "admin/berita/edit";
        }

        return "redirect:/admin/berita";
    }

    @PostMapping("/berita/save")
    public String saveBerita(@ModelAttribute("post") Berita berita,
                             @RequestParam("imageFile") MultipartFile imageFile,
                             RedirectAttributes redirectAttributes) {

        try {
            if (!imageFile.isEmpty()) {
                String fileName = FileUploadUtil.saveFile("uploads", imageFile);
                berita.setGambar("/uploads/" + fileName);
            }
        } catch (IOException e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Gagal mengunggah gambar: " + e.getMessage());
            return "redirect:/admin/berita/create";
        }

        // FIX NULL SAFETY
        if (berita.getTanggal() == null) {
            berita.setTanggal(LocalDate.now());
        }

        if (berita.getJudul() == null || berita.getJudul().trim().isEmpty()) {
            redirectAttributes.addFlashAttribute("errorMessage", "Judul tidak boleh kosong");
            return "redirect:/admin/berita/create";
        }

        beritaService.saveBerita(berita);

        redirectAttributes.addFlashAttribute("successMessage", "Berita berhasil disimpan");

        return "redirect:/admin/berita";
    }

    @GetMapping("/berita/delete/{id}")
    public String deleteBerita(@PathVariable Long id) {
        beritaService.deleteBerita(id);
        return "redirect:/admin/berita";
    }

    // ==========================================
    // WISATA
    // ==========================================
    @GetMapping("/wisata")
    public String adminWisata(Model model) {
        model.addAttribute("destinations", wisataService.getAllWisata());
        return "admin/wisata/index";
    }

    @GetMapping("/wisata/create")
    public String createWisataForm(Model model) {
        model.addAttribute("destination", new Wisata());
        return "admin/wisata/create";
    }

    @GetMapping("/wisata/edit/{id}")
    public String editWisataForm(@PathVariable Long id, Model model) {
        Optional<Wisata> wisata = wisataService.getWisataById(id);
        if (wisata.isPresent()) {
            model.addAttribute("destination", wisata.get());
            return "admin/wisata/edit";
        }
        return "redirect:/admin/wisata";
    }

    @PostMapping("/wisata/save")
    public String saveWisata(@ModelAttribute("destination") Wisata wisata,
                             @RequestParam("imageFile") MultipartFile imageFile) {
        try {
            if (!imageFile.isEmpty()) {
                String fileName = FileUploadUtil.saveFile("uploads", imageFile);
                wisata.setGambar("/uploads/" + fileName);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        wisataService.saveWisata(wisata);
        return "redirect:/admin/wisata";
    }

    @GetMapping("/wisata/delete/{id}")
    public String deleteWisata(@PathVariable Long id) {
        wisataService.deleteWisata(id);
        return "redirect:/admin/wisata";
    }

    // ==========================================
    // UMKM
    // ==========================================
    @GetMapping("/umkm")
    public String adminUmkm(Model model) {
        model.addAttribute("products", umkmService.getAllUmkm());
        return "admin/umkm/index";
    }

    @GetMapping("/umkm/create")
    public String createUmkmForm(Model model) {
        Umkm umkm = new Umkm();
        umkm.setRating(4.8);
        model.addAttribute("product", umkm);
        return "admin/umkm/create";
    }

    @GetMapping("/umkm/edit/{id}")
    public String editUmkmForm(@PathVariable Long id, Model model) {
        Optional<Umkm> umkm = umkmService.getUmkmById(id);
        if (umkm.isPresent()) {
            model.addAttribute("product", umkm.get());
            return "admin/umkm/edit";
        }
        return "redirect:/admin/umkm";
    }

    @PostMapping("/umkm/save")
    public String saveUmkm(@ModelAttribute("product") Umkm umkm,
                           @RequestParam("imageFile") MultipartFile imageFile) {

        try {
            if (!imageFile.isEmpty()) {
                String fileName = FileUploadUtil.saveFile("uploads", imageFile);
                umkm.setGambar("/uploads/" + fileName);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (umkm.getRating() == null) {
            umkm.setRating(4.8);
        }

        umkmService.saveUmkm(umkm);
        return "redirect:/admin/umkm";
    }

    @GetMapping("/umkm/delete/{id}")
    public String deleteUmkm(@PathVariable Long id) {
        umkmService.deleteUmkm(id);
        return "redirect:/admin/umkm";
    }

    // ==========================================
    // PENDUDUK
    // ==========================================
    @GetMapping("/penduduk")
    public String adminPenduduk(Model model) {
        model.addAttribute("citizens", pendudukService.getAllPenduduk());
        return "admin/penduduk/index";
    }

    @GetMapping("/penduduk/create")
    public String createPendudukForm(Model model) {
        model.addAttribute("citizen", new Penduduk());
        return "admin/penduduk/create";
    }

    @GetMapping("/penduduk/edit/{id}")
    public String editPendudukForm(@PathVariable Long id, Model model) {
        Optional<Penduduk> citizen = pendudukService.getPendudukById(id);
        if (citizen.isPresent()) {
            model.addAttribute("citizen", citizen.get());
            return "admin/penduduk/edit";
        }
        return "redirect:/admin/penduduk";
    }

    @PostMapping("/penduduk/save")
    public String savePenduduk(@ModelAttribute("citizen") Penduduk penduduk) {
        pendudukService.savePenduduk(penduduk);
        return "redirect:/admin/penduduk";
    }

    @GetMapping("/penduduk/delete/{id}")
    public String deletePenduduk(@PathVariable Long id) {
        pendudukService.deletePenduduk(id);
        return "redirect:/admin/penduduk";
    }

    // ==========================================
    // ORGANISASI
    // ==========================================
    @GetMapping("/organisasi")
    public String adminOrganisasi(Model model) {
        model.addAttribute("orgs", organisasiService.getAllOrganisasi());
        return "admin/organisasi/index";
    }

    @GetMapping("/organisasi/create")
    public String createOrganisasiForm(Model model) {
        model.addAttribute("org", new Organisasi());
        return "admin/organisasi/create";
    }

    @GetMapping("/organisasi/edit/{id}")
    public String editOrganisasiForm(@PathVariable Long id, Model model) {
        Optional<Organisasi> org = organisasiService.getOrganisasiById(id);
        if (org.isPresent()) {
            model.addAttribute("org", org.get());
            return "admin/organisasi/edit";
        }
        return "redirect:/admin/organisasi";
    }

    @PostMapping("/organisasi/save")
    public String saveOrganisasi(@ModelAttribute("org") Organisasi organisasi) {
        organisasiService.saveOrganisasi(organisasi);
        return "redirect:/admin/organisasi";
    }

    @GetMapping("/organisasi/delete/{id}")
    public String deleteOrganisasi(@PathVariable Long id) {
        organisasiService.deleteOrganisasi(id);
        return "redirect:/admin/organisasi";
    }

    // ==========================================
    // GALERI
    // ==========================================
    @GetMapping("/galeri")
    public String adminGaleri(Model model) {
        model.addAttribute("photos", galeriService.getAllGaleri());
        return "admin/galeri/index";
    }

    @GetMapping("/galeri/create")
    public String createGaleriForm(Model model) {
        model.addAttribute("photo", new Galeri());
        return "admin/galeri/create";
    }

    @PostMapping("/galeri/save")
    public String saveGaleri(@ModelAttribute("photo") Galeri galeri,
                             @RequestParam("imageFile") MultipartFile imageFile) {
        try {
            if (!imageFile.isEmpty()) {
                String fileName = FileUploadUtil.saveFile("uploads", imageFile);
                galeri.setUrlGambar("/uploads/" + fileName);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        galeriService.saveGaleri(galeri);
        return "redirect:/admin/galeri";
    }

    @GetMapping("/galeri/delete/{id}")
    public String deleteGaleri(@PathVariable Long id) {
        galeriService.deleteGaleri(id);
        return "redirect:/admin/galeri";
    }

    // ==========================================
    // LAYANAN
    // ==========================================
    @GetMapping("/layanan")
    public String adminLayanan(Model model) {
        model.addAttribute("requests", layananService.getAllRequests());
        return "admin/layanan/index";
    }

    @GetMapping("/layanan/edit/{id}")
    public String editLayananForm(@PathVariable String id, Model model) {
        Optional<Layanan> request = layananService.getRequestById(id);
        if (request.isPresent()) {
            model.addAttribute("request", request.get());
            return "admin/layanan/edit";
        }
        return "redirect:/admin/layanan";
    }

    @PostMapping("/layanan/save")
    public String saveLayanan(@ModelAttribute("request") Layanan request) {

        if (request.getStatus() == null) {
            return "redirect:/admin/layanan";
        }

        layananService.updateRequestStatus(
                request.getId(),
                request.getStatus(),
                request.getCatatanAdmin()
        );

        return "redirect:/admin/layanan";
    }
}