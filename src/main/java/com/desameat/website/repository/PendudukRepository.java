package com.desameat.website.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.desameat.website.model.Penduduk;

@Repository
public interface PendudukRepository extends JpaRepository<Penduduk, Long> {

    boolean existsByNik(String nik);

    List<Penduduk> findByNamaContainingIgnoreCase(String nama);

    // Count by jenis kelamin
    long countByJenisKelamin(Penduduk.JenisKelamin jenisKelamin);

    // Count by agama
    @Query("SELECT p.agama AS label, COUNT(p) AS total FROM Penduduk p GROUP BY p.agama")
    List<Object[]> countByAgama();

    // Count by pendidikan
    @Query("SELECT p.pendidikan AS label, COUNT(p) AS total FROM Penduduk p GROUP BY p.pendidikan")
    List<Object[]> countByPendidikan();

    // Count by pekerjaan
    @Query("SELECT p.pekerjaan AS label, COUNT(p) AS total FROM Penduduk p GROUP BY p.pekerjaan")
    List<Object[]> countByPekerjaan();

    // Count by status pernikahan
    @Query("SELECT p.statusPernikahan AS label, COUNT(p) AS total FROM Penduduk p GROUP BY p.statusPernikahan")
    List<Object[]> countByStatusPernikahan();

    // Age group distribution
    @Query(value = """
        SELECT
            CASE
                WHEN TIMESTAMPDIFF(YEAR, tanggal_lahir, CURDATE()) < 15 THEN '0-14'
                WHEN TIMESTAMPDIFF(YEAR, tanggal_lahir, CURDATE()) < 25 THEN '15-24'
                WHEN TIMESTAMPDIFF(YEAR, tanggal_lahir, CURDATE()) < 45 THEN '25-44'
                WHEN TIMESTAMPDIFF(YEAR, tanggal_lahir, CURDATE()) < 60 THEN '45-59'
                ELSE '60+'
            END AS label,
            COUNT(*) AS total
        FROM penduduk
        GROUP BY label
        ORDER BY MIN(TIMESTAMPDIFF(YEAR, tanggal_lahir, CURDATE()))
        """, nativeQuery = true)
    List<Object[]> countByKelompokUsia();
}