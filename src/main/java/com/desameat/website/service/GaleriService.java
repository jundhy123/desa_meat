package com.desameat.website.service;

import com.desameat.website.model.Galeri;
import com.desameat.website.repository.GaleriRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GaleriService {

    private final GaleriRepository galeriRepository;

    public GaleriService(GaleriRepository galeriRepository) {
        this.galeriRepository = galeriRepository;
    }

    public List<Galeri> getAllGaleri() {
        return galeriRepository.findAll();
    }

    public void saveGaleri(Galeri galeri) {
        galeriRepository.save(galeri);
    }
}