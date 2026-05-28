package com.desameat.website.controller;

import com.desameat.website.model.Pengurus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/struktur-organisasi")
public class StrukturOrganisasiController {

    @GetMapping
    public String index(Model model) {
        // Pimpinan Tertinggi
        model.addAttribute("kepalaDesa", new Pengurus("KEPALA DESA", "Janri Simanjuntak"));
        model.addAttribute("sekdes", new Pengurus("SEKRETARIS DESA", "Doharni Marpaung"));

        // Unsur Sekretariat Desa
        List<Pengurus> sekretariat = Arrays.asList(
            new Pengurus("TATA USAHA DAN UMUM", "Masrina Sianturi"),
            new Pengurus("KEUANGAN", "Tonni Tampubolon"),
            new Pengurus("KAUR PERENCANAAN", "Jarar Siahaan")
        );
        model.addAttribute("sekretariatList", sekretariat);

        // Unsur Pelaksana Teknis (Kasi)
        List<Pengurus> pelaksanaTeknis = Arrays.asList(
            new Pengurus("KASI PEMERINTAHAN", "Rita Simanjuntak"),
            new Pengurus("KASI KESEJAHTERAAN", "Hellen Simanjuntak"),
            new Pengurus("KASI PELAYANAN", "Haposan Siahaan")
        );
        model.addAttribute("kasiList", pelaksanaTeknis);

        // Unsur Kewilayahan (Kepala Dusun)
        List<Pengurus> kewilayahan = Arrays.asList(
            new Pengurus("Kepala Dusun I", "Desi P. Simanjuntak"),
            new Pengurus("Kepala Dusun II", "Golda Afni Merci Simamora"),
            new Pengurus("Kepala Dusun III", "Donald Siahaan")
        );
        model.addAttribute("kadusList", kewilayahan);

        // Mengarah ke src/main/resources/templates/admin/organisasi.html
        return "user/organisasi";
    }
}