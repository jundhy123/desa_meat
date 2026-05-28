package com.desameat.website.repository;

import com.desameat.website.model.ProfilDesa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfilRepository extends JpaRepository<ProfilDesa, Long> {
}