package com.desameat.website.service;

import com.desameat.website.model.Penduduk;
import com.desameat.website.repository.PendudukRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PendudukService {

    @Autowired
    private PendudukRepository pendudukRepository;

    public List<Penduduk> getAllPenduduk() {
        return pendudukRepository.findAll();
    }

    public Optional<Penduduk> getPendudukByNik(String nik) {
        return pendudukRepository.findByNik(nik);
    }

    public Optional<Penduduk> getPendudukById(Long id) {
        return pendudukRepository.findById(id);
    }

    public List<Penduduk> searchPenduduk(String query) {
        return pendudukRepository.findByNamaContainingIgnoreCase(query);
    }

    public Penduduk savePenduduk(Penduduk penduduk) {
        return pendudukRepository.save(penduduk);
    }

    public void deletePenduduk(Long id) {
        pendudukRepository.deleteById(id);
    }
}
