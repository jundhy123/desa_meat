package com.desameat.website.repository;

import com.desameat.website.model.ProfilDesa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfilRepository extends JpaRepository<ProfilDesa, Long> {
    Optional<ProfilDesa> findByNamaDesa(String namaDesa);
}
