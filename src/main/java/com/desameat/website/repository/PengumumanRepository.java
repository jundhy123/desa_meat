package com.desameat.website.repository;

import com.desameat.website.model.Pengumuman;
import com.desameat.website.model.Pengumuman.StatusPengumuman;
import com.desameat.website.model.Pengumuman.PrioritasPengumuman;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PengumumanRepository extends JpaRepository<Pengumuman, Long> {

    List<Pengumuman> findTop5ByStatusOrderByTanggalDibuatDesc(StatusPengumuman status);

    long countByStatus(StatusPengumuman status);

    @Query("SELECT p FROM Pengumuman p WHERE p.status = 'AKTIF' AND " +
           "(p.tanggalBerakhir IS NULL OR p.tanggalBerakhir >= :today) " +
           "ORDER BY p.prioritas DESC, p.tanggalDibuat DESC")
    List<Pengumuman> findPengumumanAktif(@Param("today") LocalDate today);

    @Query("SELECT p FROM Pengumuman p WHERE " +
           "(:keyword IS NULL OR LOWER(p.judul) LIKE LOWER(CONCAT('%', :keyword, '%'))) AND " +
           "(:status IS NULL OR p.status = :status) AND " +
           "(:prioritas IS NULL OR p.prioritas = :prioritas)")
    Page<Pengumuman> findWithFilter(@Param("keyword") String keyword,
                                    @Param("status") StatusPengumuman status,
                                    @Param("prioritas") PrioritasPengumuman prioritas,
                                    Pageable pageable);
}
