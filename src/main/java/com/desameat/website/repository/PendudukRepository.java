package com.desameat.website.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.desameat.website.model.Penduduk;

@Repository
public interface PendudukRepository extends JpaRepository<Penduduk, Long> {
    List<Penduduk> findByNamaContainingIgnoreCase(String nama);
    long countByJenisKelamin(String jenisKelamin);
}