package com.desameat.website.service;

import com.desameat.website.model.Umkm;
import com.desameat.website.repository.UmkmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UmkmService {

    @Autowired
    private UmkmRepository umkmRepository;

    public List<Umkm> getAllUmkm() {
        return umkmRepository.findAll();
    }

    public Optional<Umkm> getUmkmById(Long id) {
        return umkmRepository.findById(id);
    }

    public List<Umkm> searchUmkm(String query) {
        return umkmRepository.findByNamaProdukContainingIgnoreCase(query);
    }

    public List<Umkm> getUmkmByKategori(String kategori) {
        return umkmRepository.findByKategori(kategori);
    }

    public Umkm saveUmkm(Umkm umkm) {
        return umkmRepository.save(umkm);
    }

    public void deleteUmkm(Long id) {
        umkmRepository.deleteById(id);
    }
}
