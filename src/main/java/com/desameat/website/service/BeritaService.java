package com.desameat.website.service;

import com.desameat.website.model.Berita;
import com.desameat.website.repository.BeritaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BeritaService {

    @Autowired
    private BeritaRepository beritaRepository;

    public List<Berita> getAllBerita() {
        return beritaRepository.findAll();
    }

    public List<Berita> getRecentBerita() {
        return beritaRepository.findFirst5ByOrderByTanggalDesc();
    }

    public Optional<Berita> getBeritaById(Long id) {
        return beritaRepository.findById(id);
    }

    public List<Berita> searchBerita(String query) {
        return beritaRepository.findByJudulContainingIgnoreCase(query);
    }

    public List<Berita> getBeritaByKategori(String kategori) {
        return beritaRepository.findByKategori(kategori);
    }

    public Berita saveBerita(Berita berita) {
        return beritaRepository.save(berita);
    }

    public void deleteBerita(Long id) {
        beritaRepository.deleteById(id);
    }
}
