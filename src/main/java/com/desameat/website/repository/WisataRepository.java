package com.desameat.website.repository;

import com.desameat.website.model.Wisata;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WisataRepository extends JpaRepository<Wisata, Long> {
    List<Wisata> findByNamaContainingIgnoreCase(String nama);
    List<Wisata> findByLokasiContainingIgnoreCase(String lokasi);
}
