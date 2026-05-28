package com.desameat.website.repository;

import com.desameat.website.model.Umkm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UmkmRepository extends JpaRepository<Umkm, Long> {
    List<Umkm> findByNamaProdukContainingIgnoreCase(String query);
    List<Umkm> findByKategori(String kategori);
}
