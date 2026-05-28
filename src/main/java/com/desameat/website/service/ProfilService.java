package com.desameat.website.service;

import com.desameat.website.model.ProfilDesa;
import com.desameat.website.repository.ProfilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfilService {

    @Autowired
    private ProfilRepository profilRepository;

    public Optional<ProfilDesa> getProfil() {
        // Return latest or first profile
        return profilRepository.findAll().stream().findFirst();
    }

    public ProfilDesa saveProfil(ProfilDesa profilDesa) {
        return profilRepository.save(profilDesa);
    }
}
