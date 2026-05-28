package com.desameat.website.service;

import com.desameat.website.model.Galeri;
import com.desameat.website.repository.GaleriRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GaleriService {

    @Autowired
    private GaleriRepository galeriRepository;

    public List<Galeri> getAllGaleri() {
        return galeriRepository.findAll();
    }

    public List<Galeri> getGaleriByKategori(String kategori) {
        return galeriRepository.findByKategori(kategori);
    }

    public Optional<Galeri> getGaleriById(Long id) {
        return galeriRepository.findById(id);
    }

    public Galeri saveGaleri(Galeri galeri) {
        return galeriRepository.save(galeri);
    }

    public void deleteGaleri(Long id) {
        galeriRepository.deleteById(id);
    }
}
