package com.desameat.website.service;

import com.desameat.website.model.Umkm;
import com.desameat.website.repository.UmkmRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UmkmService {

    private final UmkmRepository umkmRepository;

    public UmkmService(UmkmRepository umkmRepository) {
        this.umkmRepository = umkmRepository;
    }

    public List<Umkm> getAllUMKM() {
        return umkmRepository.findAll();
    }

    public void saveUMKM(Umkm umkm) {
        umkmRepository.save(umkm);
    }
}