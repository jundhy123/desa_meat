package com.desameat.website.service;

import com.desameat.website.model.Layanan;
import com.desameat.website.repository.LayananRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class LayananService {

    @Autowired
    private LayananRepository repository;

    public List<String> getAllTypesList() {
        return Arrays.asList(
            "Surat Keterangan Usaha (SKU)",
            "Surat Keterangan Tidak Mampu (SKTM)",
            "Surat Pengantar Domisili",
            "Surat Keterangan Kelahiran"
        );
    }

    public List<Layanan> getAllRequests() {
        return repository.findAll();
    }

    public Optional<Layanan> getRequestById(String id) {
        return repository.findById(id);
    }

    public Layanan createRequest(Layanan l) {
        String randomId = "SRT-2026-" + (int)(100 + Math.random() * 900);
        l.setId(randomId);
        l.setTanggalPengajuan(LocalDate.now());
        l.setStatus(Layanan.StatusSurat.PENDING);
        return repository.save(l);
    }

    public List<Layanan> searchByNikOrId(String query) {
        return repository.findByIdContainingOrNikContaining(query, query);
    }

    public Layanan updateRequestStatus(String id, Layanan.StatusSurat newStatus, String adminNotes) {
        Optional<Layanan> opt = repository.findById(id);
        if (opt.isPresent()) {
            Layanan l = opt.get();
            l.setStatus(newStatus);
            l.setCatatanAdmin(adminNotes);
            return repository.save(l);
        }
        throw new RuntimeException("Request with ID " + id + " not found!");
    }
}
