package com.desameat.website.service;

import com.desameat.website.model.Berita;
import com.desameat.website.model.Berita.KategoriBerita;
import com.desameat.website.model.Berita.StatusBerita;
import com.desameat.website.repository.BeritaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BeritaService {

    @Autowired
    private BeritaRepository beritaRepository;

    @Value("${app.upload.dir:uploads/}")
    private String uploadDir;

    public Berita simpan(Berita berita, MultipartFile gambar) throws IOException {
        if (gambar != null && !gambar.isEmpty()) {
            berita.setGambar(simpanGambar(gambar));
        }
        return beritaRepository.save(berita);
    }

    public List<Berita> semuaBerita() {
        return beritaRepository.findAll(Sort.by(Sort.Direction.DESC, "tanggalDibuat"));
    }

    public Optional<Berita> cariById(Long id) {
        return beritaRepository.findById(id);
    }

    public Page<Berita> cariDenganFilter(String keyword, String kategori, String status, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "tanggalDibuat"));
        KategoriBerita kat = (kategori != null && !kategori.isEmpty()) ? KategoriBerita.valueOf(kategori) : null;
        StatusBerita stat = (status != null && !status.isEmpty()) ? StatusBerita.valueOf(status) : null;
        String kw = (keyword != null && !keyword.isEmpty()) ? keyword : null;
        return beritaRepository.findWithFilter(kw, kat, stat, pageable);
    }

    public List<Berita> beritaTerbaru() {
        return beritaRepository.findTop5ByStatusOrderByTanggalDibuatDesc(StatusBerita.PUBLISHED);
    }

    public Berita update(Long id, Berita beritaBaru, MultipartFile gambar) throws IOException {
        Berita berita = beritaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Berita tidak ditemukan"));
        berita.setJudul(beritaBaru.getJudul());
        berita.setKonten(beritaBaru.getKonten());
        berita.setKategori(beritaBaru.getKategori());
        berita.setStatus(beritaBaru.getStatus());
        berita.setPenulis(beritaBaru.getPenulis());
        if (gambar != null && !gambar.isEmpty()) {
            if (berita.getGambar() != null) hapusGambar(berita.getGambar());
            berita.setGambar(simpanGambar(gambar));
        }
        return beritaRepository.save(berita);
    }

    public void ubahStatus(Long id, StatusBerita status) {
        Berita berita = beritaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Berita tidak ditemukan"));
        berita.setStatus(status);
        beritaRepository.save(berita);
    }

    public void hapus(Long id) {
        Berita berita = beritaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Berita tidak ditemukan"));
        if (berita.getGambar() != null) hapusGambar(berita.getGambar());
        beritaRepository.delete(berita);
    }

    public long hitungTotal() { return beritaRepository.count(); }
    public long hitungByStatus(StatusBerita status) { return beritaRepository.countByStatus(status); }
    public long hitungByKategori(KategoriBerita kategori) { return beritaRepository.countByKategori(kategori); }

    private String simpanGambar(MultipartFile file) throws IOException {
        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)) Files.createDirectories(uploadPath);
        String namaFile = UUID.randomUUID() + "_" + file.getOriginalFilename();
        Files.copy(file.getInputStream(), uploadPath.resolve(namaFile));
        return namaFile;
    }

    private void hapusGambar(String namaFile) {
        try { Files.deleteIfExists(Paths.get(uploadDir).resolve(namaFile)); }
        catch (IOException e) { System.out.println("Gagal hapus gambar: " + e.getMessage()); }
    }
}
