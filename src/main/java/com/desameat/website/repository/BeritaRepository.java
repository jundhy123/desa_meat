package com.desameat.website.repository;

import com.desameat.website.model.Berita;
import com.desameat.website.model.Berita.KategoriBerita;
import com.desameat.website.model.Berita.StatusBerita;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BeritaRepository extends JpaRepository<Berita, Long> {

    // Untuk tabel terbaru di dashboard
    List<Berita> findTop5ByStatusOrderByTanggalDibuatDesc(StatusBerita status);

    // Untuk hitung statistik
    long countByStatus(StatusBerita status);
    long countByKategori(KategoriBerita kategori);

    // Untuk filter + search di halaman list berita
    @Query("SELECT b FROM Berita b WHERE " +
           "(:keyword IS NULL OR LOWER(b.judul) LIKE LOWER(CONCAT('%', :keyword, '%'))) AND " +
           "(:kategori IS NULL OR b.kategori = :kategori) AND " +
           "(:status IS NULL OR b.status = :status)")
    Page<Berita> findWithFilter(
        @Param("keyword") String keyword,
        @Param("kategori") KategoriBerita kategori,
        @Param("status") StatusBerita status,
        Pageable pageable
    );
}