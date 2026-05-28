package com.desameat.website.repository;

import com.desameat.website.model.Penduduk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PendudukRepository extends JpaRepository<Penduduk, Long> {
    Optional<Penduduk> findByNik(String nik);
    List<Penduduk> findByNamaContainingIgnoreCase(String nama);
    boolean existsByNik(String nik);
}
