package com.desameat.website.service;

import com.desameat.website.model.*;
import com.desameat.website.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfilService {

    @Autowired
    private ProfilRepository profilRepository;

    @Autowired
    private VisiRepository visiRepository;

    @Autowired
    private MisiRepository misiRepository;

    // ===== PROFIL =====
    public List<ProfilDesa> getAllProfil() {
        return profilRepository.findAll();
    }

    public ProfilDesa getProfilById(Long id) {
        return profilRepository.findById(id).orElse(null);
    }

    public ProfilDesa saveProfil(ProfilDesa profil) {
        return profilRepository.save(profil);
    }

    public void deleteProfil(Long id) {
        profilRepository.deleteById(id);
    }

    // ===== VISI =====
    public List<Visi> getAllVisi() {
        return visiRepository.findAll();
    }

    public Visi saveVisi(Visi visi) {
        return visiRepository.save(visi);
    }

    public void deleteVisi(Long id) {
        visiRepository.deleteById(id);
    }

    public Optional<Visi> getVisiById(Long id) {
        return visiRepository.findById(id);
    }

    // ===== MISI =====
    public List<Misi> getAllMisi() {
        return misiRepository.findAll();
    }

    public Misi saveMisi(Misi misi) {
        return misiRepository.save(misi);
    }

    public void deleteMisi(Long id) {
        misiRepository.deleteById(id);
    }

    public Optional<Misi> getMisiById(Long id) {
        return misiRepository.findById(id);
    }
}