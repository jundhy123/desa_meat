package com.desameat.website.service;

import com.desameat.website.model.Wisata;
import com.desameat.website.repository.WisataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WisataService {

    @Autowired
    private WisataRepository wisataRepository;

    public List<Wisata> getAllWisata() {
        return wisataRepository.findAll();
    }

    public Optional<Wisata> getWisataById(Long id) {
        return wisataRepository.findById(id);
    }

    public List<Wisata> searchWisata(String query) {
        return wisataRepository.findByNamaContainingIgnoreCase(query);
    }

    public Wisata saveWisata(Wisata wisata) {
        return wisataRepository.save(wisata);
    }

    public void deleteWisata(Long id) {
        wisataRepository.deleteById(id);
    }
}
