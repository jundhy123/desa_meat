package com.desameat.website.repository;

import com.desameat.website.model.Berita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BeritaRepository extends JpaRepository<Berita, Long> {
    List<Berita> findByJudulContainingIgnoreCase(String judul);
    List<Berita> findByKategori(String kategori);
    List<Berita> findFirst5ByOrderByTanggalDesc();
}
