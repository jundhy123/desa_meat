package com.desameat.website.repository;

import com.desameat.website.model.Layanan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LayananRepository extends JpaRepository<Layanan, String> {
    List<Layanan> findByIdContainingOrNikContaining(String id, String nik);
    List<Layanan> findByNik(String nik);
}
