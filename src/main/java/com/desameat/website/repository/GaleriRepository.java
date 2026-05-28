package com.desameat.website.repository;

import com.desameat.website.model.Galeri;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GaleriRepository extends JpaRepository<Galeri, Long> {
    List<Galeri> findByKategori(String kategori);
}
