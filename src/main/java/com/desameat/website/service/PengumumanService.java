package com.desameat.website.service;

import com.desameat.website.model.Pengumuman;
import com.desameat.website.model.Pengumuman.StatusPengumuman;
import com.desameat.website.model.Pengumuman.PrioritasPengumuman;
import com.desameat.website.repository.PengumumanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PengumumanService {

    @Autowired
    private PengumumanRepository pengumumanRepository;

    public Pengumuman simpan(Pengumuman pengumuman) {
        return pengumumanRepository.save(pengumuman);
    }

    public Optional<Pengumuman> cariById(Long id) {
        return pengumumanRepository.findById(id);
    }

    public Page<Pengumuman> cariDenganFilter(String keyword, String status, String prioritas, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "tanggalDibuat"));
        StatusPengumuman stat = (status != null && !status.isEmpty()) ? StatusPengumuman.valueOf(status) : null;
        PrioritasPengumuman prior = (prioritas != null && !prioritas.isEmpty()) ? PrioritasPengumuman.valueOf(prioritas) : null;
        String kw = (keyword != null && !keyword.isEmpty()) ? keyword : null;
        return pengumumanRepository.findWithFilter(kw, stat, prior, pageable);
    }

    public List<Pengumuman> pengumumanTerbaru() {
        return pengumumanRepository.findTop5ByStatusOrderByTanggalDibuatDesc(StatusPengumuman.AKTIF);
    }

    public Pengumuman update(Long id, Pengumuman baru) {
        Pengumuman p = pengumumanRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pengumuman tidak ditemukan"));
        p.setJudul(baru.getJudul());
        p.setIsi(baru.getIsi());
        p.setTanggalMulai(baru.getTanggalMulai());
        p.setTanggalBerakhir(baru.getTanggalBerakhir());
        p.setPrioritas(baru.getPrioritas());
        p.setStatus(baru.getStatus());
        p.setDibuatOleh(baru.getDibuatOleh());
        return pengumumanRepository.save(p);
    }

    public void ubahStatus(Long id, StatusPengumuman status) {
        Pengumuman p = pengumumanRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pengumuman tidak ditemukan"));
        p.setStatus(status);
        pengumumanRepository.save(p);
    }

    public void hapus(Long id) {
        if (!pengumumanRepository.existsById(id))
            throw new RuntimeException("Pengumuman tidak ditemukan");
        pengumumanRepository.deleteById(id);
    }

    public long hitungTotal() { return pengumumanRepository.count(); }
    public long hitungByStatus(StatusPengumuman status) { return pengumumanRepository.countByStatus(status); }
}
