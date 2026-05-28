package com.desameat.website.service;

import com.desameat.website.model.Organisasi;
import com.desameat.website.repository.OrganisasiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrganisasiService {

    @Autowired
    private OrganisasiRepository organisasiRepository;

    public List<Organisasi> getAllOrganisasi() {
        return organisasiRepository.findAll();
    }

    public Optional<Organisasi> getOrganisasiById(Long id) {
        return organisasiRepository.findById(id);
    }

    public List<Organisasi> searchOrganisasi(String query) {
        return organisasiRepository.findByNamaContainingIgnoreCase(query);
    }

    public Organisasi saveOrganisasi(Organisasi organisasi) {
        return organisasiRepository.save(organisasi);
    }

    public void deleteOrganisasi(Long id) {
        organisasiRepository.deleteById(id);
    }
}
