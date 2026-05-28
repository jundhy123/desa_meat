package com.desameat.website.controller;

import java.util.ArrayList; 
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.desameat.website.model.Penduduk;
import com.desameat.website.service.PendudukService;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired 
    private PendudukService service;

    // 1. HALAMAN DASHBOARD ADMIN
    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("activePage", "dashboard");

        // Ambil map statistik gabungan dari PendudukService dengan aman
        Map<String, Object> pendudukStats;
        try {
            pendudukStats = service.getStatistik();
            if (pendudukStats == null) {
                pendudukStats = new HashMap<>();
            }
        } catch (Exception e) {
            pendudukStats = new HashMap<>();
        }

        // Sediakan isi Map 'stats' untuk data Berita & Pengumuman agar tidak error
        Map<String, Object> stats = new HashMap<>();
        stats.put("totalBerita", 0); 
        stats.put("beritaPublished", 0);
        stats.put("totalPengumuman", 0); 
        stats.put("pengumumanAktif", 0);
        stats.put("beritaDraft", 0);                     
        stats.put("pengumumanNonaktif", 0);              
        stats.put("beritaKategoriBerita", 0);            
        stats.put("beritaKategoriPengumuman", 0);        
        stats.put("beritaKategoriAcara", 0);             
        stats.put("beritaKategoriInformasi", 0);          
        model.addAttribute("stats", stats);

        // Kirim data statistik penduduk ke HTML Dashboard
        model.addAttribute("totalPenduduk", pendudukStats.getOrDefault("total", 0));
        model.addAttribute("statGender", pendudukStats.getOrDefault("gender", new HashMap<>()));
        model.addAttribute("statPekerjaan", pendudukStats.getOrDefault("pekerjaan", new HashMap<>()));

        // Sediakan objek list kosong untuk tabel bawah dashboard
        model.addAttribute("beritaTerbaru", new ArrayList<>());
        model.addAttribute("pengumumanTerbaru", new ArrayList<>());

        return "admin/dashboard";
    }

    // 2. HALAMAN UTAMA PENDUDUK (Aman dari bentrok fragment navbar & database)
    @GetMapping("/penduduk")
    public String penduduk(Model model, @RequestParam(required = false) String cari) {
        model.addAttribute("activePage", "penduduk");
        
        // Ambil statistik asli, jika gagal/null buatkan fallback kosongan agar fragment navbar tidak Error 500
        Map<String, Object> pendudukStats;
        try {
            pendudukStats = service.getStatistik();
            if (pendudukStats == null) {
                pendudukStats = new HashMap<>();
            }
        } catch (Exception e) {
            pendudukStats = new HashMap<>();
        }
        // Pastikan variabel 'total' ada di dalam map statistik
        pendudukStats.putIfAbsent("total", 0);
        model.addAttribute("statistik", pendudukStats);
        
        // Sediakan isi Map 'stats' kosong juga di halaman ini, berjaga-jaga jika fragment sidebar memanggilnya
        Map<String, Object> stats = new HashMap<>();
        stats.put("totalBerita", 0);
        stats.put("totalPengumuman", 0);
        model.addAttribute("stats", stats);
        
        // Proteksi awal flash attribute 'sukses'
        if (!model.containsAttribute("sukses")) {
            model.addAttribute("sukses", null);
        }
        
        // Ambil data asli dari database dengan pengaman try-catch
        try {
            if (cari != null && !cari.isEmpty()) {
                model.addAttribute("pendudukList", service.cariByNama(cari));
                model.addAttribute("cari", cari);
            } else {
                model.addAttribute("pendudukList", service.getAllPenduduk());
            }
        } catch (Exception e) {
            // Jika database kosong atau query eror, gagalkan ke list kosong (mencegah layaran putih 500)
            model.addAttribute("pendudukList", new ArrayList<Penduduk>());
        }
        
        return "admin/penduduk"; 
    }

    // 3. HALAMAN FORM TAMBAH PENDUDUK
    @GetMapping("/penduduk/tambah")
    public String formTambah(Model model) {
        model.addAttribute("activePage", "penduduk");
        model.addAttribute("penduduk", new Penduduk()); 
        return "admin/penduduk-form";
    }

    // 4. HALAMAN FORM EDIT PENDUDUK
    @GetMapping("/penduduk/edit/{id}")
    public String formEdit(@PathVariable Long id, Model model) {
        try {
            Penduduk p = service.getById(id);
            if (p == null) return "redirect:/admin/penduduk";
            model.addAttribute("activePage", "penduduk");
            model.addAttribute("penduduk", p); 
            return "admin/penduduk-form";
        } catch (Exception e) {
            return "redirect:/admin/penduduk";
        }
    }

    // 5. PROSES SIMPAN DATA (POST)
    @PostMapping("/penduduk/simpan")
    public String simpan(@ModelAttribute Penduduk p, RedirectAttributes ra) {
        try {
            service.simpan(p);
            ra.addFlashAttribute("sukses", "Data penduduk berhasil disimpan!");
        } catch (Exception e) {
            ra.addFlashAttribute("error", "Gagal menyimpan data!");
        }
        return "redirect:/admin/penduduk";
    }

    // 6. PROSES HAPUS DATA (POST)
    @PostMapping("/penduduk/hapus/{id}")
    public String hapus(@PathVariable Long id, RedirectAttributes ra) {
        try {
            service.hapus(id);
            ra.addFlashAttribute("sukses", "Data penduduk berhasil dihapus!");
        } catch (Exception e) {
            ra.addFlashAttribute("error", "Gagal menghapus data!");
        }
        return "redirect:/admin/penduduk";
    }
}