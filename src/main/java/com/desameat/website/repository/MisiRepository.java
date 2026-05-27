package com.desameat.website.repository;

import com.desameat.website.model.Misi;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MisiRepository extends JpaRepository<Misi, Long> {
}